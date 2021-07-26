package ch.rbarton.intellijsv.core.resolve.impl

import ch.rbarton.intellijsv.core.psi.SvModuleDeclaration
import ch.rbarton.intellijsv.core.psi.SvUtil
import ch.rbarton.intellijsv.core.psi.ext.SvReferenceElement
import ch.rbarton.intellijsv.core.resolve.SvPolyReference
import ch.rbarton.intellijsv.ide.SvIcons
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.ResolveResult

/**
 * Defines how to resolve a reference to a Module
 */
class SvModuleReferenceImpl(element: SvReferenceElement) : SvPolyReference<SvReferenceElement>(element)
{
    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult>
    {
        if (myElement?.referenceElement == null) return ResolveResult.EMPTY_ARRAY
        return SvUtil.findModuleIdentifiers(myElement.project, myElement.referenceElement!!.text)
            .map { PsiElementResolveResult(it.identifier) }.toTypedArray()
    }

    override fun resolve(): PsiElement?
    {
        val resolveResults = multiResolve(false)
        return if (resolveResults.size == 1) resolveResults[0].element else null
    }

    override fun getVariants(): Array<out LookupElement>
    {
        val variants: MutableList<LookupElement> = mutableListOf()
        SvUtil.findModuleIdentifiers(myElement.project).forEach {
            variants += LookupElementBuilder.create(it.identifier).withIcon(SvIcons.SV_MODULE).withTypeText(it.containingFile.text)
        }
        return variants.toTypedArray()
    }

    override fun isReferenceTo(element: PsiElement): Boolean =
        element is SvModuleDeclaration && super.isReferenceTo(element.identifier)
}