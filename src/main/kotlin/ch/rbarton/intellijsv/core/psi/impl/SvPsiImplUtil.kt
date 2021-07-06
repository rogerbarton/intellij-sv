package ch.rbarton.intellijsv.core.psi.impl

import ch.rbarton.intellijsv.core.SvIcons
import ch.rbarton.intellijsv.core.psi.SvElementFactory
import ch.rbarton.intellijsv.core.psi.SvModuleDeclaration
import ch.rbarton.intellijsv.core.psi.SvTypes
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import javax.swing.Icon


class SvPsiImplUtil
{
    companion object
    {
        @JvmStatic
        fun getName(element: SvModuleDeclaration): String? = element.getIdentifier.text

        @JvmStatic
        fun getNameIdentifier(element: SvModuleDeclaration): PsiElement = element.getIdentifier

        @JvmStatic
        fun setName(element: SvModuleDeclaration, newName: String): PsiElement
        {
            val idNode: ASTNode = element.node.findChildByType(SvTypes.IDENTIFIER) ?: return element

            val module: SvModuleDeclaration = SvElementFactory.createModuleDeclaration(element.project, newName)
            val newIdNode: ASTNode = module.firstChild.node
            element.node.replaceChild(idNode, newIdNode)

            return element
        }

        @JvmStatic
        fun getPresentation(element: SvModuleDeclaration): ItemPresentation
        {
            return object : ItemPresentation
            {
                override fun getPresentableText(): String? = element.text
                override fun getLocationString(): String = element.containingFile.name
                override fun getIcon(unused: Boolean): Icon = SvIcons.SV_FILE
            }

        }


    }
}