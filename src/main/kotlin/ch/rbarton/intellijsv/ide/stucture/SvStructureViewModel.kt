package ch.rbarton.intellijsv.ide.stucture

import ch.rbarton.intellijsv.core.psi.*
import ch.rbarton.intellijsv.core.psi.ext.SvNamedIdentifierOwner
import ch.rbarton.intellijsv.ide.SvIcons
import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.StructureViewModelBase
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.*
import com.intellij.openapi.editor.Editor

class SvStructureViewModel(editor: Editor?, file: SvFile) :
    StructureViewModelBase(file, editor, SvStructureViewElement(file)),
    StructureViewModel.ElementInfoProvider
{
    init
    {
        withSuitableClasses(
            SvNamedIdentifierOwner::class.java,
        )
        withSorters(
            Sorter.ALPHA_SORTER,
        )
    }

    override fun getFilters(): Array<Filter> = arrayOf(
        SvParameterFilter,
        SvPortFilter,
        SvModuleItemFilter
    )

    override fun isAlwaysShowsPlus(element: StructureViewTreeElement): Boolean = element.value is SvFile

    override fun isAlwaysLeaf(element: StructureViewTreeElement): Boolean = when (element.value)
    {
        // TODO: not sure what to put here
        is SvContinuousAssign,
        is SvExpr -> true
        else -> false
    }

    //region Filters
    object SvParameterFilter : Filter
    {
        override fun isVisible(treeNode: TreeElement?): Boolean
        {
            val element = (treeNode as? SvStructureViewElement)?.value
            return element !is SvParameterDeclaration
        }

        override fun isReverted(): Boolean = true

        override fun getPresentation(): ActionPresentation =
            ActionPresentationData("Show Module Parameters", null, SvIcons.SV_PARAM)

        override fun getName() = ID

        const val ID = "SV_PARAMETER_FILTER"
    }

    object SvPortFilter : Filter
    {
        override fun isVisible(treeNode: TreeElement?): Boolean
        {
            val element = (treeNode as? SvStructureViewElement)?.value
            return element !is SvPortDeclaration
        }

        override fun isReverted(): Boolean = true

        override fun getPresentation(): ActionPresentation =
            ActionPresentationData("Show Module Ports", null, SvIcons.SV_PORT_INPUT)

        override fun getName() = ID

        const val ID = "SV_PORT_FILTER"
    }

    object SvModuleItemFilter : Filter
    {
        override fun isVisible(treeNode: TreeElement?): Boolean
        {
            val element = (treeNode as? SvStructureViewElement)?.value
            return element !is SvModuleItem
        }

        override fun isReverted(): Boolean = true

        override fun getPresentation(): ActionPresentation =
            ActionPresentationData("Show Module Items", null, SvIcons.SV_MODULE_INSTANCE)

        override fun getName() = ID

        const val ID = "SV_MODULE_ITEM_FILTER"
    }
    //endregion
}