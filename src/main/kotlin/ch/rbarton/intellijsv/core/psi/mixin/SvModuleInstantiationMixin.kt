package ch.rbarton.intellijsv.core.psi.mixin

import ch.rbarton.intellijsv.core.psi.SvModuleInstantiation
import ch.rbarton.intellijsv.core.psi.ext.SvNamedIdentifierOwnerImpl
import ch.rbarton.intellijsv.core.resolve.impl.SvModuleReferenceImpl
import ch.rbarton.intellijsv.ide.SvIcons
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import javax.swing.Icon

/**
 * Adds additional functionality for reference resolution for the module being instantiated.
 */
abstract class SvModuleInstantiationMixin(node: ASTNode) : SvModuleInstantiation, SvNamedIdentifierOwnerImpl(node)
{
    override fun getBaseIcon(): Icon? = SvIcons.SV_MODULE_INSTANCE

    // -- SvNameIdentifierOwner
    override fun getNameIdentifier(): PsiElement? = instanceIdentifier

    // -- SvReferenceElement Impl
    override val referenceElement: PsiElement get() = moduleIdentifier
    override fun getReference(): PsiReference = SvModuleReferenceImpl(this)
}