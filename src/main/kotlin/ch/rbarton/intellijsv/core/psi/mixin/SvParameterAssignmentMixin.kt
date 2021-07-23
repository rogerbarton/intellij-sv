package ch.rbarton.intellijsv.core.psi.mixin

import ch.rbarton.intellijsv.core.psi.SvParameterDeclaration
import ch.rbarton.intellijsv.core.psi.ext.SvNamedIdentifierOwnerImpl
import ch.rbarton.intellijsv.ide.SvIcons
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import javax.swing.Icon

abstract class SvParameterDeclarationMixin(node: ASTNode) : SvParameterDeclaration, SvNamedIdentifierOwnerImpl(node)
{
    override fun getBaseIcon(): Icon = when {
        parameterType.localparam != null -> SvIcons.SV_LOCALPARAM
        parameterType.parameter != null -> SvIcons.SV_PARAM
        else -> SvIcons.SV_PARAM
    }

    // -- SvNameIdentifierOwner
    override fun getNameIdentifier(): PsiElement? = identifier
}