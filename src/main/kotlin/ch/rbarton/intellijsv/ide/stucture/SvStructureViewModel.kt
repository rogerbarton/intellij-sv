package ch.rbarton.intellijsv.ide.stucture

import ch.rbarton.intellijsv.core.psi.*
import ch.rbarton.intellijsv.core.psi.ext.SvNamedIdentifierOwner
import ch.rbarton.intellijsv.ide.SvIcons
import com.intellij.icons.AllIcons
import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.StructureViewModelBase
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.*
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiElement

/**
 * Defines which sorters and filters are available for the structure tree.
 * Creates the rootElement.
 */
class SvStructureViewModel(editor: Editor?, file: SvFile, rootElement: PsiElement) :
    StructureViewModelBase(file, editor, SvStructureViewElement(rootElement)),
    StructureViewModel.ElementInfoProvider
{
    init
    {
        withSuitableClasses(
            SvNamedIdentifierOwner::class.java,
        )
        withSorters(
            Sorter.ALPHA_SORTER,
            SvTypeSorter
        )
    }

    override fun getFilters(): Array<Filter> = arrayOf(
        SvParameterFilter,
        SvPortFilter,
        SvModuleInternalsFilter,
        SvAlwaysInitialFilter
    )

    override fun isAlwaysShowsPlus(element: StructureViewTreeElement): Boolean = element.value is SvFile

    override fun isAlwaysLeaf(element: StructureViewTreeElement): Boolean = when (element.value)
    {
        is SvContinuousAssign,
        is SvParameterDeclaration,
        is SvNetDeclaration,
        is SvTypeDeclaration -> true
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

    object SvModuleInternalsFilter : Filter
    {
        override fun isVisible(treeNode: TreeElement?): Boolean
        {
            val element = (treeNode as? SvStructureViewElement)?.value
            val isItem = element is SvModuleItem &&
                    !(element.alwaysConstruct != null || element.initialConstruct != null || element.finalConstruct != null)
            return !isItem
        }

        override fun isReverted(): Boolean = true

        override fun getPresentation(): ActionPresentation =
            ActionPresentationData("Show Internal Module Declarations", null, SvIcons.SV_MODULE_ITEM)

        override fun getName() = ID

        const val ID = "SV_MODULE_INTERNALS_FILTER"
    }

    object SvAlwaysInitialFilter : Filter
    {
        override fun isVisible(treeNode: TreeElement?): Boolean
        {
            val element = (treeNode as? SvStructureViewElement)?.value
            val isAlways = element is SvModuleItem &&
                    (element.alwaysConstruct != null || element.initialConstruct != null || element.finalConstruct != null)
            return !isAlways
        }

        override fun isReverted(): Boolean = true

        override fun getPresentation(): ActionPresentation =
            ActionPresentationData("Show Always, Initial, Final", null, SvIcons.SV_ALWAYS)

        override fun getName() = ID

        const val ID = "SV_ALWAYS_INITIAL_FILTER"
    }
    //endregion

    //region Sorters
    object SvTypeSorter : Sorter
    {
        override fun getComparator() = Comparator<Any> { a1, a2 -> a1.typeLevel() - a2.typeLevel() }

        private fun Any.typeLevel(): Int
        {
            val psi: PsiElement = (this as? SvStructureViewElement)?.value ?: return Int.MAX_VALUE
            return when (psi)
            {
                is SvParameterDeclaration -> if (psi.parameterType.parameter != null) 0 else 1
                is SvPortDeclaration -> when
                {
                    psi.portDirection.input != null -> 10
                    psi.portDirection.inout != null -> 11
                    psi.portDirection.ref != null -> 12
                    psi.portDirection.output != null -> 13
                    else -> 19
                }
                is SvModuleItem -> when
                {
                    psi.timeunitsDeclaration != null -> 20
                    psi.parameterDeclaration != null -> 21
                    psi.typeDeclaration != null -> 22
                    psi.netDeclaration != null -> 23
                    psi.moduleInstantiation != null -> 24
                    psi.generateRegion != null -> 25
                    psi.alwaysConstruct != null -> 26
                    psi.initialConstruct != null -> 27
                    psi.finalConstruct != null -> 28
                    else -> 29
                }
                else -> Int.MAX_VALUE
            }
        }

        override fun getPresentation(): ActionPresentation =
            ActionPresentationData("Sort by Type", null, AllIcons.ObjectBrowser.SortByType)

        override fun isVisible() = true

        override fun getName() = ID

        const val ID = "SV_KIND_SORTER"
    }
    //endregion
}