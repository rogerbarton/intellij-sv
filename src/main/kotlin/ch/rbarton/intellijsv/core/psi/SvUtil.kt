package ch.rbarton.intellijsv.core.psi

import ch.rbarton.intellijsv.SvFileType
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil

class SvUtil
{
    companion object
    {
        fun findModuleIdentifiers(project: Project, query: String? = null): List<SvModuleDeclaration>
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

        fun findPortIdentifiers(containingModule: SvModuleDeclaration, query: String? = null): List<SvPortDeclaration>
        {
            if (containingModule.moduleHeader == null) return emptyList()

            return containingModule.moduleHeader!!.portDeclarationList.filter {
                it.identifier?.text.equals(query)
            }
        }

        fun findPortNetIdentifiers(
            containingModule: SvModuleDeclaration,
            query: String? = null
        ): List<SvPortDeclaration>
        {
            if (containingModule.moduleHeader == null) return emptyList()

            return containingModule.moduleHeader!!.portDeclarationList.filter {
                it.identifier?.text.equals(query)
            }
        }

        fun findInnerNetIdentifiers(
            containingModule: SvModuleDeclaration,
            query: String? = null
        ): List<Pair<SvNetDeclaration, PsiElement>>
        {
            val result: MutableList<Pair<SvNetDeclaration, PsiElement>> = mutableListOf()

            // Get NetDeclarations with a valid containing identifier, note: it may have multiple
            val validDecls: List<SvNetDeclaration> =
                containingModule.moduleItemList.filter {
                    it.netDeclaration != null
                            && it.netDeclaration!!.netDeclarationAssignmentList.any { idd ->
                        idd.identifier.text.equals(
                            query
                        )
                    }
                }.map { it.netDeclaration!! }

            // Extract NetDecl and Identifier pairs
            for (decl in validDecls)
            {
                for (identifierWithDefault in decl.netDeclarationAssignmentList)
                {
                    if (identifierWithDefault.identifier.text.equals(query))
                        result += Pair(decl, identifierWithDefault.identifier)
                }
            }

            return result
        }

        fun findPortParameterIdentifiers(
            containingModule: SvModuleDeclaration,
            query: String? = null
        ): List<SvParameterDeclaration>
        {
            if (containingModule.moduleHeader == null) return emptyList()

            return containingModule.moduleHeader!!.parameterDeclarationList.filter {
                it.identifier?.text.equals(query)
            }
        }

        fun findInnerParameterIdentifiers(
            containingModule: SvModuleDeclaration,
            query: String? = null
        ): List<SvParameterDeclaration>
        {
            val result: MutableList<SvParameterDeclaration> = mutableListOf()

            val validDecls: List<SvParameterDeclaration> =
                containingModule.moduleItemList.filter { it.parameterDeclaration != null }
                    .map { it.parameterDeclaration!! }

            // Extract NetDecl and Identifier pairs
            validDecls
                .filter { it.identifier?.text.equals(query) }
                .forEach { result += it }

            return result
        }
    }
}
