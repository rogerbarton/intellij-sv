package ch.rbarton.intellijsv.core.psi.mixin

import ch.rbarton.intellijsv.core.psi.SvModuleIdentifier
import ch.rbarton.intellijsv.core.resolve.impl.SvModuleReferenceImpl
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference

abstract class SvModuleIdentifierMixin(node: ASTNode) : SvModuleIdentifier, ASTWrapperPsiElement(node)
{
    // -- SvReferenceElement Impl
    override val referenceElement: PsiElement get() = identifier
    override fun getReference(): PsiReference = SvModuleReferenceImpl(this)
}