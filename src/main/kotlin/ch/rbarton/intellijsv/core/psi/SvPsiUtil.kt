package ch.rbarton.intellijsv.core.psi

import ch.rbarton.intellijsv.SvFileType
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil

class SvPsiUtil
{
    companion object
    {
        fun findModuleDeclarations(project: Project, query: String? = null): List<SvModuleDeclaration>
        {
            val result: MutableList<SvModuleDeclaration> = mutableListOf()
            val virtualFiles: Collection<VirtualFile> =
                FileTypeIndex.getFiles(SvFileType.INSTANCE, GlobalSearchScope.allScope(project))

            for (virtualFile: VirtualFile in virtualFiles)
            {
                val svFile: SvFile = PsiManager.getInstance(project).findFile(virtualFile) as SvFile
                val moduleDecls: Array<SvModuleDeclaration> =
                    PsiTreeUtil.getChildrenOfType(svFile, SvModuleDeclaration::class.java) ?: continue

                if (query == null)
                    result += moduleDecls
                else
                    result += moduleDecls.filter { it.identifier.text.equals(query) }.toList()
            }
            return result
        }

        fun findPortNets(
            containingModule: SvModuleDeclaration,
            query: String? = null
        ): List<SvPortDeclaration>
        {
            if (containingModule.moduleHeader == null) return emptyList()

            val results = containingModule.moduleHeader!!.portDeclarationList.toMutableList()
            if (query != null)
                results.retainAll { it.identifier?.text.equals(query) }

            return results
        }

        fun findInnerNets(
            containingModule: SvModuleDeclaration,
            query: String? = null
        ): List<Pair<SvNetDeclaration, SvNetDeclarationAssignment>>
        {
            val result: MutableList<Pair<SvNetDeclaration, SvNetDeclarationAssignment>> = mutableListOf()

            // Get NetDeclarations with a valid containing identifier, note: it may have multiple
            for (decl in containingModule.moduleItemList)
            {
                if (decl.netDeclaration == null) continue
                for (identifierWithDefault in decl.netDeclaration!!.netDeclarationAssignmentList)
                {
                    if (query == null || identifierWithDefault.identifier.text.equals(query))
                        result += Pair(decl.netDeclaration!!, identifierWithDefault)
                }
            }

            return result
        }

        fun findPortParameters(
            containingModule: SvModuleDeclaration,
            query: String? = null
        ): List<SvParameterDeclaration>
        {
            if (containingModule.moduleHeader == null) return emptyList()

            val results = containingModule.moduleHeader!!.parameterDeclarationList.toMutableList()
            if (query != null)
                results.retainAll { it.identifier?.text.equals(query) }

            return results
        }

        fun findInnerParameters(
            containingModule: SvModuleDeclaration,
            query: String? = null
        ): List<SvParameterDeclaration>
        {
            val result: MutableList<SvParameterDeclaration> = mutableListOf()

            val validDecls: MutableList<SvParameterDeclaration> =
                containingModule.moduleItemList.filter { it.parameterDeclaration != null }
                    .map { it.parameterDeclaration!! }.toMutableList()

            if (query != null)
                validDecls.retainAll { it.identifier?.text.equals(query) }

            validDecls.forEach { result += it }

            return result
        }
    }
}
