package ch.rbarton.intellijsv.core.psi.mixin

import ch.rbarton.intellijsv.core.psi.SvModuleInstantiation
import ch.rbarton.intellijsv.core.resolve.impl.SvModuleReferenceImpl
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference

/**
 * Adds additional functionality for reference resolution for the module being instantiated.
 */
abstract class SvModuleInstantiationMixin(node: ASTNode) : SvModuleInstantiation, ASTWrapperPsiElement(node)
{
    override val referenceNameElement: PsiElement? get() = moduleIdentifier

    override fun getReference(): PsiReference? = SvModuleReferenceImpl(this)
}