package ch.rbarton.intellijsv.core.psi.mixin

import ch.rbarton.intellijsv.core.psi.SvModuleInstantiation
import ch.rbarton.intellijsv.core.psi.ext.SvNamedIdentifierOwnerImpl
import ch.rbarton.intellijsv.ide.SvIcons
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import javax.swing.Icon

/**
 * Adds additional functionality for reference resolution for the module being instantiated.
 */
abstract class SvModuleInstantiationMixin(node: ASTNode) : SvModuleInstantiation, SvNamedIdentifierOwnerImpl(node)
{
    override fun getIcon(flags: Int): Icon? = SvIcons.SV_MODULE_INSTANCE

    // -- SvNameIdentifierOwner
    override fun getNameIdentifier(): PsiElement? = instanceIdentifier
}