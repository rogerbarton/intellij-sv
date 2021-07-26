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
    override val referenceElement: PsiElement? get() = identifier

    override fun getReference(): PsiReference?
    {
        if (identifier == null) return null
        return when (parent)
        {
            is SvModuleDeclaration -> object : SvMonoReference<SvLabel>(this, identifier!!.textRangeInParent) {
                override fun resolve(): PsiElement = (parent as SvModuleDeclaration).identifier
                override fun isReferenceTo(element: PsiElement): Boolean = element is SvModuleDeclaration && element == parent
            }
            else -> null
        }

    }
}