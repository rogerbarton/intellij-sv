package ch.rbarton.intellijsv.core.resolve

import ch.rbarton.intellijsv.core.psi.ext.SvReferenceElement
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*

/**
 * Abstract class for reference resolution.
 * PolyVariant means a reference can resolve to multiple targets.
 * We have an Impl class for each kind of reference we can resolve, e.g. to a module or variable.
 */
abstract class SvPolyReference<T : SvReferenceElement>(element: T) :
    PsiPolyVariantReferenceBase<T>(element, element.referenceElement?.textRangeInParent), PsiPolyVariantReference

/**
 * References at most one identifier
 */
abstract class SvMonoReference<T : SvReferenceElement> :
    PsiReferenceBase<T>, PsiReference
{
    constructor(element: T) : super(element, element.referenceElement?.textRangeInParent)
    constructor(element: T, textRangeInElement: TextRange) : super(element, textRangeInElement)
}