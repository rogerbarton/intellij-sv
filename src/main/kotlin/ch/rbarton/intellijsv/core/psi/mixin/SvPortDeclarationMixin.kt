package ch.rbarton.intellijsv.core.psi.mixin

import ch.rbarton.intellijsv.core.psi.SvPortDeclaration
import ch.rbarton.intellijsv.core.psi.ext.SvNamedIdentifierOwnerImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

abstract class SvPortDeclarationMixin(node: ASTNode) : SvPortDeclaration, SvNamedIdentifierOwnerImpl(node)
{
    // -- SvNameIdentifierOwner
    override fun getNameIdentifier(): PsiElement? = identifier
}