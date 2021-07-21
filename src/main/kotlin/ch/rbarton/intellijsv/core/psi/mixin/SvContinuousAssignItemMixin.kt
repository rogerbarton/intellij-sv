package ch.rbarton.intellijsv.core.psi.mixin

import ch.rbarton.intellijsv.core.psi.SvContinuousAssignItem
import ch.rbarton.intellijsv.core.psi.SvModuleDeclaration
import ch.rbarton.intellijsv.core.psi.SvModuleInstantiation
import ch.rbarton.intellijsv.core.resolve.impl.SvModuleReferenceImpl
import ch.rbarton.intellijsv.core.resolve.impl.SvNetReferenceImpl
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference

/**
 * Adds additional functionality for reference resolution for the module being instantiated.
 */
abstract class SvContinuousAssignItemMixin(node: ASTNode) : SvContinuousAssignItem, ASTWrapperPsiElement(node)
{
    // -- SvReferenceElement Impl
    override val referenceElement: PsiElement get() = netLvalue.hierarchicalIdentifier
    override fun getReference(): PsiReference = SvNetReferenceImpl(this)
}