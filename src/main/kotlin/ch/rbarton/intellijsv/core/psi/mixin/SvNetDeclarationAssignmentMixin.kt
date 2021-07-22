package ch.rbarton.intellijsv.core.psi.mixin

import ch.rbarton.intellijsv.core.psi.SvNetDeclarationAssignment
import ch.rbarton.intellijsv.core.psi.ext.SvNamedIdentifierOwnerImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

abstract class SvNetDeclarationAssignmentMixin(node: ASTNode) : SvNetDeclarationAssignment, SvNamedIdentifierOwnerImpl(node)
{
    // -- SvNameIdentifierOwner
    override fun getNameIdentifier(): PsiElement? = identifier
}