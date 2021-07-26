package ch.rbarton.intellijsv.core.psi.mixin

import ch.rbarton.intellijsv.core.psi.SvAlwaysConstruct
import ch.rbarton.intellijsv.ide.SvIcons
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import javax.swing.Icon

abstract class SvAlwaysConstructMixin(node: ASTNode) : SvAlwaysConstruct, ASTWrapperPsiElement(node)
{
    override fun getIcon(flags: Int): Icon = when
    {
        alwaysKeyword.alwaysFf != null -> SvIcons.SV_ALWAYS_FF
        alwaysKeyword.alwaysComb != null -> SvIcons.SV_ALWAYS_COMB
        alwaysKeyword.alwaysLatch != null -> SvIcons.SV_ALWAYS_LATCH
        else -> SvIcons.SV_ALWAYS
    }
}