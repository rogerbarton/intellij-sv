package ch.rbarton.intellijsv.core.psi.mixin

import ch.rbarton.intellijsv.core.psi.SvModuleDeclaration
import ch.rbarton.intellijsv.core.psi.ext.SvNamedIdentifierOwnerImpl
import ch.rbarton.intellijsv.ide.SvIcons
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import javax.swing.Icon

abstract class SvModuleDeclarationMixin(node: ASTNode) : SvModuleDeclaration, SvNamedIdentifierOwnerImpl(node)
{
    override fun getIcon(flags: Int): Icon? = SvIcons.SV_MODULE

    // -- SvNameIdentifierOwner
    override fun getNameIdentifier(): PsiElement = identifier

    // TODO: not sure what the use of this is
//    override fun getIdentifyingElement(): PsiElement? = this
}