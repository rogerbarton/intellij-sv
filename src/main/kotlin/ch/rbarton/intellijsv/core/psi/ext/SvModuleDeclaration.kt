package ch.rbarton.intellijsv.core.psi.ext

import ch.rbarton.intellijsv.core.SvIcons
import ch.rbarton.intellijsv.core.psi.SvModuleDeclaration
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import javax.swing.Icon

abstract class SvModuleDeclarationMixin(node: ASTNode): SvNamedElementImpl(node), SvModuleDeclaration {
    override fun getNameIdentifier(): PsiElement = identifier

    override fun getPresentation(): ItemPresentation
    {
        return object : ItemPresentation
        {
            override fun getPresentableText(): String? = text
            override fun getLocationString(): String = containingFile.name
            override fun getIcon(unused: Boolean): Icon = SvIcons.SV_FILE
        }
    }
}