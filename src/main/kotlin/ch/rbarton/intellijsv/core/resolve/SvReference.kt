package ch.rbarton.intellijsv.core.resolve

import ch.rbarton.intellijsv.core.psi.SvHierarchicalIdentifier
import ch.rbarton.intellijsv.core.psi.SvIdentifierRule
import ch.rbarton.intellijsv.core.psi.SvPsiFactory
import ch.rbarton.intellijsv.core.psi.SvTypes.IDENTIFIER
import ch.rbarton.intellijsv.core.psi.ext.SvReferenceElement
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.util.elementType

/**
 * Abstract class for reference resolution.
 * PolyVariant means a reference can resolve to multiple targets.
 * We have an Impl class for each kind of reference we can resolve, e.g. to a module or variable.
 */
abstract class SvPolyReference<T : SvReferenceElement>(element: T) :
    PsiPolyVariantReferenceBase<T>(element, element.referenceElement?.textRangeInParent), PsiPolyVariantReference
{
    final override fun getRangeInElement(): TextRange = super.getRangeInElement()

    final override fun calculateDefaultRangeInElement(): TextRange
    {
        val anchor = element.referenceElement ?: return TextRange.EMPTY_RANGE
        if (anchor.parent === element)
            return TextRange.from(anchor.startOffsetInParent, anchor.textLength)
        check(anchor.parent.parent === element)
        return TextRange.from(anchor.startOffsetInParent + anchor.parent.startOffsetInParent, anchor.textLength)
    }

    override fun getVariants(): Array<out LookupElement> = LookupElement.EMPTY_ARRAY

    override fun equals(other: Any?): Boolean = other is SvPolyReference<*> && element === other.element

    override fun hashCode(): Int = element.hashCode()

    override fun handleElementRename(newName: String): PsiElement
    {
        if (element.referenceElement != null)
            doRename(element.referenceElement!!, newName)
        return element
    }

    companion object
    {
        @JvmStatic
        protected fun doRename(identifier: PsiElement, newName: String)
        {
            if (identifier.elementType == IDENTIFIER)
                identifier.replace(SvPsiFactory(identifier.project).createIdentifier(newName))
            else when (identifier)
            {
                is SvIdentifierRule -> identifier.replace(SvPsiFactory(identifier.project).createIdentifier(newName))
                is SvHierarchicalIdentifier -> identifier.identifier.replace(
                    SvPsiFactory(identifier.project).createHierarchicalIdentifier(newName)
                )
                else -> error("Unsupported identifier type for `$newName` (${identifier.elementType})")
            }
        }
    }
}

/**
 * References at most one identifier
 */
abstract class SvMonoReference<T : SvReferenceElement> :
    PsiReferenceBase<T>, PsiReference
{
    constructor(element: T) : super(element, element.referenceElement?.textRangeInParent)
    constructor(element: T, textRangeInElement: TextRange) : super(element, textRangeInElement)

    final override fun getRangeInElement(): TextRange = super.getRangeInElement()

    final override fun calculateDefaultRangeInElement(): TextRange
    {
        val anchor = element.referenceElement ?: return TextRange.EMPTY_RANGE
        if (anchor.parent === element)
            return TextRange.from(anchor.startOffsetInParent, anchor.textLength)
        check(anchor.parent.parent === element)
        return TextRange.from(anchor.startOffsetInParent + anchor.parent.startOffsetInParent, anchor.textLength)
    }
}