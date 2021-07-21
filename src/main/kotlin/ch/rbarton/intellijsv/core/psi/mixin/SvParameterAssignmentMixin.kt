package ch.rbarton.intellijsv.core.psi.mixin

import ch.rbarton.intellijsv.core.psi.SvParameterDeclaration
import ch.rbarton.intellijsv.core.psi.ext.SvNameIdentifierOwnerImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

abstract class SvParameterDeclarationMixin(node: ASTNode) : SvParameterDeclaration, SvNameIdentifierOwnerImpl(node)
{
    // -- SvNameIdentifierOwner
    override fun getNameIdentifier(): PsiElement? = identifier
}