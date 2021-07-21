package ch.rbarton.intellijsv.core.resolve.impl

import ch.rbarton.intellijsv.ide.SvIcons
import ch.rbarton.intellijsv.core.psi.SvModuleDeclaration
import ch.rbarton.intellijsv.core.psi.SvNetDeclaration
import ch.rbarton.intellijsv.core.psi.SvPortDeclaration
import ch.rbarton.intellijsv.core.psi.SvUtil
import ch.rbarton.intellijsv.core.psi.ext.SvReferenceElement
import ch.rbarton.intellijsv.core.resolve.SvPolyReference
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
class SvNetReferenceImpl(element: SvReferenceElement) : SvPolyReference<SvReferenceElement>(element)
{
    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult>
    {
        if (myElement?.referenceElement == null) return ResolveResult.EMPTY_ARRAY
        val queryId: String = myElement.referenceElement!!.text

        val parentModule: SvModuleDeclaration = PsiTreeUtil.getParentOfType(myElement, SvModuleDeclaration::class.java)
            ?: return ResolveResult.EMPTY_ARRAY

        val portDecls =
            SvUtil.findPortNetIdentifiers(parentModule, queryId).map { PsiElementResolveResult(it.identifier!!) }
        val netDecls = SvUtil.findInnerNetIdentifiers(parentModule, queryId).map { PsiElementResolveResult(it.second) }

        return (portDecls + netDecls).toTypedArray()
    }

    override fun getVariants(): Array<out LookupElement>
    {
        val variants: MutableList<LookupElement> = mutableListOf()

        val parentModule: SvModuleDeclaration = PsiTreeUtil.getParentOfType(myElement, SvModuleDeclaration::class.java)
            ?: return LookupElement.EMPTY_ARRAY

        SvUtil.findPortNetIdentifiers(parentModule, null).forEach {
            variants += LookupElementBuilder.create(it).withIcon(SvIcons.SV_NET)
        }
        SvUtil.findInnerNetIdentifiers(parentModule, null).forEach {
            variants += LookupElementBuilder.create(it).withIcon(SvIcons.SV_NET)
        }

        return variants.toTypedArray()
    }

    override fun isReferenceTo(element: PsiElement): Boolean =
        (element is SvNetDeclaration || element is SvPortDeclaration) && super.isReferenceTo(element)
}