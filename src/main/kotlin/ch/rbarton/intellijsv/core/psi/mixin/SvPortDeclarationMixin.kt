package ch.rbarton.intellijsv.core.psi.mixin

import ch.rbarton.intellijsv.core.psi.SvPortDeclaration
import ch.rbarton.intellijsv.core.psi.ext.SvNameIdentifierOwnerImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

abstract class SvPortDeclarationMixin(node: ASTNode) : SvPortDeclaration, SvNameIdentifierOwnerImpl(node)
{
    // -- SvNameIdentifierOwner
    override fun getNameIdentifier(): PsiElement? = identifier
}