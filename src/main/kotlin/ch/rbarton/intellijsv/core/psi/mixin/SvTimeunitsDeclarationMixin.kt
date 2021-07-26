package ch.rbarton.intellijsv.core.psi.mixin

import ch.rbarton.intellijsv.core.psi.SvTimeunitsDeclaration
import ch.rbarton.intellijsv.ide.SvIcons
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import javax.swing.Icon

abstract class SvTimeunitsDeclarationMixin(node: ASTNode) : SvTimeunitsDeclaration, ASTWrapperPsiElement(node)
{
    override fun getIcon(flags: Int): Icon = SvIcons.SV_TIME
}