package ch.rbarton.intellijsv.core.psi.mixin

import ch.rbarton.intellijsv.core.psi.SvLabel
import ch.rbarton.intellijsv.core.psi.SvModuleDeclaration
import ch.rbarton.intellijsv.core.resolve.SvMonoReference
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference

abstract class SvLabelMixin(node: ASTNode) : SvLabel, ASTWrapperPsiElement(node)
{
    // -- SvReferenceElement
    override val referenceIdentifier: PsiElement? get() = identifier

    override fun getReference(): PsiReference?
    {
        if (identifier == null) return null
        return when (parent)
        {
            // TODO: somehow this reference is not found by Find Usages
            is SvModuleDeclaration -> object : SvMonoReference<SvLabel>(this, identifier!!.textRangeInParent) {
                override fun resolve(): PsiElement = parent
                override fun isReferenceTo(element: PsiElement): Boolean = element is SvModuleDeclaration && super.isReferenceTo(element)
                override fun getVariants(): Array<Any> = arrayOf(parent)
            }
            else -> null
        }
    }
}