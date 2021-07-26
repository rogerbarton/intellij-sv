package ch.rbarton.intellijsv.ide.stucture

import ch.rbarton.intellijsv.core.psi.SvFile
import ch.rbarton.intellijsv.core.psi.SvModuleDeclaration
import ch.rbarton.intellijsv.ide.presentation.getPresentationStructureView
import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.TreeAnchorizer
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.pom.Navigatable
import com.intellij.psi.PsiElement

/**
 * Defines a node in the Structure view tree and which children are shown.
 */
class SvStructureViewElement(psiArg: PsiElement) : StructureViewTreeElement
{
    // TreeAnchorizer used for performance
    private val psiAnchor = TreeAnchorizer.getService().createAnchor(psiArg)
    private val psi: PsiElement? get() = TreeAnchorizer.getService().retrieveElement(psiAnchor) as? PsiElement

    override fun navigate(requestFocus: Boolean)
    {
        (psi as? Navigatable)?.navigate(requestFocus)
    }

    override fun canNavigate(): Boolean = (psi as? Navigatable)?.canNavigate() == true

    override fun canNavigateToSource(): Boolean = (psi as? Navigatable)?.canNavigateToSource() == true

    override fun getValue(): PsiElement? = psi

    override fun getPresentation(): ItemPresentation =
        psi?.let(::getPresentationStructureView) ?: PresentationData("", null, null, null)

    override fun getChildren(): Array<TreeElement> = childElements.map(::SvStructureViewElement).toTypedArray()

    private val childElements: List<PsiElement>
        get()
        {
            return when (val psi = psi)
            {
                is SvFile ->
                {
                    val result: MutableList<PsiElement> = mutableListOf()
                    for (item in psi.children)
                        if (item is SvModuleDeclaration) result += item
                    result
                }
                is SvModuleDeclaration ->
                {
                    val result: MutableList<PsiElement> = mutableListOf()
                    psi.moduleHeader?.parameterDeclarationList?.let { result.addAll(it) }
                    psi.moduleHeader?.portDeclarationList?.let { result.addAll(it) }
                    result.addAll(psi.moduleItemList.filter {
                        it.parameterDeclaration != null || it.typeDeclaration != null
                                || it.netDeclaration != null || it.moduleInstantiation != null || it.alwaysConstruct != null
                                || it.initialConstruct != null || it.finalConstruct != null || it.generateRegion != null
                    })
                    result
                }
//                is SvAlwaysConstruct -> (psi.statement as? SvSeqBlock)?.statementList ?: emptyList()
                else -> emptyList()
            }
        }
}