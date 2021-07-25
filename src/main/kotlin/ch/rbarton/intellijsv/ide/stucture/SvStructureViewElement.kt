package ch.rbarton.intellijsv.ide.stucture

import ch.rbarton.intellijsv.core.psi.SvAlwaysConstruct
import ch.rbarton.intellijsv.core.psi.SvFile
import ch.rbarton.intellijsv.core.psi.SvModuleDeclaration
import ch.rbarton.intellijsv.core.psi.SvSeqBlock
import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.TreeAnchorizer
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.navigation.NavigationItem
import com.intellij.pom.Navigatable
import com.intellij.psi.PsiElement

class SvStructureViewElement(psiArg: PsiElement) : StructureViewTreeElement
{
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
        (psi as? NavigationItem)?.presentation ?: PresentationData("yolo", null, null, null)

    override fun getChildren(): Array<TreeElement>
    {
        return childElements.map(::SvStructureViewElement).toTypedArray()
    }

    private val childElements: List<PsiElement>
        get()
        {
            return when (val psi = psi)
            {
                is SvFile ->
                {
                    val result: MutableList<PsiElement> = mutableListOf()
                    for (item in psi.children)
                    {
                        when (item)
                        {
                            is SvModuleDeclaration -> result += item
                        }
                    }
                    result
                }
                is SvModuleDeclaration ->
                {
                    val result: MutableList<PsiElement> = mutableListOf()
                    psi.moduleHeader?.parameterDeclarationList?.let { result.addAll(it) }
                    psi.moduleHeader?.portDeclarationList?.let { result.addAll(it) }
                    result += psi.moduleItemList
                    result
                }
                // TODO: test this, add presentation data
                is SvAlwaysConstruct -> (psi.statement as? SvSeqBlock)?.statementList ?: emptyList()
                else -> emptyList()
            }
        }
}