package ch.rbarton.intellijsv.core.resolve

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiPolyVariantReference
import com.intellij.psi.PsiPolyVariantReferenceBase

/**
 * Abstract class for reference resolution.
 * PolyVariant means a reference can resolve to multiple targets.
 * We have an Impl class for each kind of reference we can resolve, e.g. to a module or variable.
 */
abstract class SvReference(element: PsiElement, textRange: TextRange) :
    PsiPolyVariantReferenceBase<PsiElement>(element, textRange), PsiPolyVariantReference
{
    override fun getVariants(): Array<out LookupElement> = LookupElement.EMPTY_ARRAY
}