package ch.rbarton.intellijsv.core.psi.mixin

import ch.rbarton.intellijsv.core.psi.SvModuleItem
import ch.rbarton.intellijsv.ide.SvIcons
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import javax.swing.Icon

abstract class SvModuleItemMixin(node: ASTNode) : SvModuleItem, ASTWrapperPsiElement(node)
{
    override fun getIcon(flags: Int): Icon
    {
        return when
        {
            // TODO: may need to change this when the items "extend" ModuleItem in BNF like with expressions
            continuousAssign != null -> continuousAssign!!.getIcon(flags)
            timeunitsDeclaration != null -> timeunitsDeclaration!!.getIcon(flags)
            moduleInstantiation != null -> moduleInstantiation!!.getIcon(flags)
            moduleDeclaration != null -> moduleDeclaration!!.getIcon(flags)
            alwaysConstruct != null -> alwaysConstruct!!.getIcon(flags)
            parameterDeclaration != null -> parameterDeclaration!!.getIcon(flags)
            initialConstruct != null -> SvIcons.SV_INITIAL
            finalConstruct != null -> SvIcons.SV_FINAL
            generateRegion != null -> SvIcons.SV_GENERATE
            netDeclaration != null -> SvIcons.SV_NET
            typeDeclaration != null -> SvIcons.SV_TYPE
            else -> SvIcons.SV_MODULE_ITEM
        }
    }
}