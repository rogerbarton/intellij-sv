package ch.rbarton.intellijsv.core.resolve.impl

import ch.rbarton.intellijsv.core.psi.SvModuleDeclaration
import ch.rbarton.intellijsv.core.psi.SvPsiUtil
import ch.rbarton.intellijsv.core.psi.ext.SvReferenceOwner
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
class SvModuleReferenceImpl(owner: SvReferenceOwner) : SvPolyReference<SvReferenceOwner>(owner)
{
    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult>
    {
        if (myElement?.referenceIdentifier == null) return ResolveResult.EMPTY_ARRAY
        return SvPsiUtil.findModuleDeclarations(myElement.project, myElement.referenceIdentifier!!.text)
            .map { PsiElementResolveResult(it) }.toTypedArray()
    }

    override fun resolve(): PsiElement?
    {
        val resolveResults = multiResolve(false)
        return if (resolveResults.size == 1) resolveResults[0].element else null
    }

    override fun getVariants(): Array<out LookupElement>
    {
        val variants: MutableList<LookupElement> = mutableListOf()
        SvPsiUtil.findModuleDeclarations(myElement.project).forEach {
            variants += LookupElementBuilder.create(it).withIcon(SvIcons.SV_MODULE).withTypeText(it.containingFile.text)
        }
        return variants.toTypedArray()
    }

    override fun isReferenceTo(element: PsiElement): Boolean =
        element is SvModuleDeclaration && super.isReferenceTo(element)
}