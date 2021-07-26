package ch.rbarton.intellijsv.core.resolve

import ch.rbarton.intellijsv.core.psi.SvHierarchicalIdentifier
import ch.rbarton.intellijsv.core.psi.SvPsiFactory
import ch.rbarton.intellijsv.core.psi.SvTypes.IDENTIFIER
import ch.rbarton.intellijsv.core.psi.ext.SvReferenceOwner
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.util.elementType

/**
 * Abstract class for reference resolution.
 * PolyVariant means a reference can resolve to multiple targets.
 * We have an Impl class for each kind of reference we can resolve, e.g. to a module or variable.
 */
abstract class SvPolyReference<T : SvReferenceOwner>(owner: T) :
    PsiPolyVariantReferenceBase<T>(owner, owner.referenceIdentifier?.textRangeInParent), PsiPolyVariantReference
{
    final override fun getRangeInElement(): TextRange = super.getRangeInElement()

    final override fun calculateDefaultRangeInElement(): TextRange
    {
        val anchor = element.referenceIdentifier ?: return TextRange.EMPTY_RANGE
        if (anchor.parent === element)
            return TextRange.from(anchor.startOffsetInParent, anchor.textLength)
        check(anchor.parent.parent === element)
        return TextRange.from(anchor.startOffsetInParent + anchor.parent.startOffsetInParent, anchor.textLength)
    }

    override fun handleElementRename(newName: String): PsiElement
    {
        if (element.referenceIdentifier != null)
            doRename(element.referenceIdentifier!!, newName)
        return element
    }
}

/**
 * References at most one identifier
 */
abstract class SvMonoReference<T : SvReferenceOwner> :
    PsiReferenceBase<T>, PsiReference
{
    constructor(element: T) : super(element, element.referenceIdentifier?.textRangeInParent)
    constructor(element: T, textRangeInElement: TextRange) : super(element, textRangeInElement)

    final override fun getRangeInElement(): TextRange = super.getRangeInElement()

    final override fun calculateDefaultRangeInElement(): TextRange
    {
        val anchor = element.referenceIdentifier ?: return TextRange.EMPTY_RANGE
        if (anchor.parent === element)
            return TextRange.from(anchor.startOffsetInParent, anchor.textLength)
        check(anchor.parent.parent === element)
        return TextRange.from(anchor.startOffsetInParent + anchor.parent.startOffsetInParent, anchor.textLength)
    }

    override fun handleElementRename(newName: String): PsiElement
    {
        if (element.referenceIdentifier != null)
            doRename(element.referenceIdentifier!!, newName)
        return element
    }
}

private fun doRename(identifier: PsiElement, newName: String)
{
    if (identifier.elementType == IDENTIFIER)
        identifier.replace(SvPsiFactory(identifier.project).createIdentifier(newName))
    else when (identifier)
    {
        is SvHierarchicalIdentifier -> identifier.identifier.replace(
            SvPsiFactory(identifier.project).createHierarchicalIdentifier(newName)
        )
        else -> error("Unsupported identifier type for `$newName` (${identifier.elementType})")
    }
}