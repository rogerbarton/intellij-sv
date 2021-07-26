package ch.rbarton.intellijsv.core.psi.mixin

import ch.rbarton.intellijsv.core.psi.SvTypeDeclaration
import ch.rbarton.intellijsv.core.psi.ext.SvNamedIdentifierOwnerImpl
import ch.rbarton.intellijsv.ide.SvIcons
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import javax.swing.Icon

abstract class SvTypeDeclarationMixin(node: ASTNode) : SvTypeDeclaration, SvNamedIdentifierOwnerImpl(node)
{
    override fun getIcon(flags: Int): Icon = SvIcons.SV_TYPE

    // -- SvNameIdentifierOwner
    override fun getNameIdentifier(): PsiElement? = identifier
}