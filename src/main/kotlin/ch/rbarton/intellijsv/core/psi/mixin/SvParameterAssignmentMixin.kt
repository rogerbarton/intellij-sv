package ch.rbarton.intellijsv.core.psi.mixin

import ch.rbarton.intellijsv.core.psi.SvParameterAssignment
import ch.rbarton.intellijsv.core.psi.ext.SvNameIdentifierOwnerImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

abstract class SvParameterAssignmentMixin(node: ASTNode) : SvParameterAssignment, SvNameIdentifierOwnerImpl(node)
{
    // -- SvNameIdentifierOwner
    override fun getNameIdentifier(): PsiElement? = identifier
}