package ch.rbarton.intellijsv.core.psi.mixin

import ch.rbarton.intellijsv.core.psi.SvPortDeclaration
import ch.rbarton.intellijsv.core.psi.ext.SvNamedIdentifierOwnerImpl
import ch.rbarton.intellijsv.ide.SvIcons
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import javax.swing.Icon

abstract class SvPortDeclarationMixin(node: ASTNode) : SvPortDeclaration, SvNamedIdentifierOwnerImpl(node)
{
    override fun getIcon(flags: Int): Icon = when
    {
        portDirection.input != null -> SvIcons.SV_PORT_INPUT
        portDirection.output != null -> SvIcons.SV_PORT_OUTPUT
        portDirection.inout != null -> SvIcons.SV_PORT_INOUT
        portDirection.ref != null -> SvIcons.SV_PORT_REF
        else -> SvIcons.SV_PORT_INPUT
    }

    // -- SvNameIdentifierOwner
    override fun getNameIdentifier(): PsiElement? = identifier
}