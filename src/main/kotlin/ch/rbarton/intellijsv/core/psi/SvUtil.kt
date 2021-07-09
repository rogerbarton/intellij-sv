package ch.rbarton.intellijsv.core.psi

import ch.rbarton.intellijsv.SvFileType
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil

class SvUtil
{
    companion object {
        fun findModuleIdentifiers(project: Project, id: String? = null): List<SvModuleDeclaration>
        {
            val result: MutableList<SvModuleDeclaration> = mutableListOf()
            val virtualFiles: Collection<VirtualFile> = FileTypeIndex.getFiles(SvFileType.INSTANCE, GlobalSearchScope.allScope(project))

            for(virtualFile: VirtualFile in virtualFiles)
            {
                val svFile: SvFile = PsiManager.getInstance(project).findFile(virtualFile) as SvFile ?: continue
                val moduleDecls: Array<SvModuleDeclaration> = PsiTreeUtil.getChildrenOfType(svFile, SvModuleDeclaration::class.java) ?: continue

                if (id == null)
                    result += moduleDecls
                else
                    result += moduleDecls.filter { it.identifier.equals(id) }.toList()
            }
            return result
        }
    }
}
