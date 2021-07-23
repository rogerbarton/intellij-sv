package ch.rbarton.intellijsv.core.psi.mixin

import ch.rbarton.intellijsv.core.psi.SvNetDeclarationAssignment
import ch.rbarton.intellijsv.core.psi.ext.SvNamedIdentifierOwnerImpl
import ch.rbarton.intellijsv.ide.SvIcons
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import javax.swing.Icon

abstract class SvNetDeclarationAssignmentMixin(node: ASTNode) : SvNetDeclarationAssignment, SvNamedIdentifierOwnerImpl(node)
{
    override fun getIcon(flags: Int): Icon = SvIcons.SV_NET

    // -- SvNameIdentifierOwner
    override fun getNameIdentifier(): PsiElement? = identifier
}