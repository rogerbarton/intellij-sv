package ch.rbarton.intellijsv.core.resolve.impl

import ch.rbarton.intellijsv.core.psi.SvModuleDeclaration
import ch.rbarton.intellijsv.core.psi.SvNetDeclarationAssignment
import ch.rbarton.intellijsv.core.psi.SvPortDeclaration
import ch.rbarton.intellijsv.core.psi.SvPsiUtil
import ch.rbarton.intellijsv.core.psi.ext.SvReferenceOwner
import ch.rbarton.intellijsv.core.resolve.SvPolyReference
import ch.rbarton.intellijsv.ide.SvIcons
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.ResolveResult
import com.intellij.psi.util.PsiTreeUtil

/**
 * Defines how to resolve a reference to a net declaration.
 * Searches in module ports and containing net declarations.
 */
class SvNetReferenceImpl(owner: SvReferenceOwner) : SvPolyReference<SvReferenceOwner>(owner)
{
    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult>
    {
        if (myElement?.referenceIdentifier == null) return ResolveResult.EMPTY_ARRAY
        val queryId: String = myElement.referenceIdentifier!!.text

        val parentModule: SvModuleDeclaration = PsiTreeUtil.getParentOfType(myElement, SvModuleDeclaration::class.java)
            ?: return ResolveResult.EMPTY_ARRAY

        val portDecls = SvPsiUtil.findPortNets(parentModule, queryId).map { PsiElementResolveResult(it) }
        val netDecls = SvPsiUtil.findInnerNets(parentModule, queryId).map { PsiElementResolveResult(it.second) }

        return (portDecls + netDecls).toTypedArray()
    }

    override fun getVariants(): Array<out LookupElement>
    {
        val variants: MutableList<LookupElement> = mutableListOf()

        val parentModule: SvModuleDeclaration = PsiTreeUtil.getParentOfType(myElement, SvModuleDeclaration::class.java)
            ?: return LookupElement.EMPTY_ARRAY

        SvPsiUtil.findPortNets(parentModule, null).forEach {
            variants += LookupElementBuilder.create(it).withIcon(SvIcons.SV_NET)
        }
        SvPsiUtil.findInnerNets(parentModule, null).forEach {
            variants += LookupElementBuilder.create(it.second).withIcon(SvIcons.SV_NET)
        }

        return variants.toTypedArray()
    }

    override fun isReferenceTo(element: PsiElement): Boolean
    {
        if (element is SvPortDeclaration)
            return if (element.identifier == null) false else super.isReferenceTo(element)
        else if (element is SvNetDeclarationAssignment)
            return super.isReferenceTo(element)

        return false
    }
}