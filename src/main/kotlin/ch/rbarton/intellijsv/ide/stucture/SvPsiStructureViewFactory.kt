package ch.rbarton.intellijsv.ide.stucture

import ch.rbarton.intellijsv.core.psi.SvFile
import ch.rbarton.intellijsv.core.psi.SvModuleDeclaration
import com.intellij.ide.structureView.StructureViewBuilder
import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder
import com.intellij.lang.PsiStructureViewFactory
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile

/**
 * Defines the Structure view panel, usually found in the bottom left of the IDE.
 * The structure is a subset of the PSI tree, with only the important elements shown.
 */
class SvPsiStructureViewFactory : PsiStructureViewFactory
{
    override fun getStructureViewBuilder(psiFile: PsiFile): StructureViewBuilder
    {
        return object : TreeBasedStructureViewBuilder()
        {
            override fun createStructureViewModel(editor: Editor?): StructureViewModel
            {
                // If we only have one module in the file make it the root in the tree
                val svFile = psiFile as SvFile
                val modules = svFile.children.filterIsInstance<SvModuleDeclaration>()
                return SvStructureViewModel(editor, svFile, if (modules.count() == 1) modules[0] else svFile)
            }
        }
    }

}