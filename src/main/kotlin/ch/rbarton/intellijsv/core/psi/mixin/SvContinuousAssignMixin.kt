package ch.rbarton.intellijsv.core.psi.mixin

import ch.rbarton.intellijsv.core.psi.SvContinuousAssign
import ch.rbarton.intellijsv.ide.SvIcons
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import javax.swing.Icon

abstract class SvContinuousAssignMixin(node: ASTNode) : SvContinuousAssign, ASTWrapperPsiElement(node)
{
    override fun getIcon(flags: Int): Icon = SvIcons.SV_CONTINUOUS_ASSIGN
}