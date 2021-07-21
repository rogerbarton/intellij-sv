package ch.rbarton.intellijsv.core.psi.mixin

import ch.rbarton.intellijsv.ide.SvIcons
import ch.rbarton.intellijsv.core.psi.SvModuleDeclaration
import ch.rbarton.intellijsv.core.psi.ext.SvNameIdentifierOwnerImpl
import ch.rbarton.intellijsv.core.psi.ext.SvReferenceElement
import ch.rbarton.intellijsv.core.resolve.SvMonoReference
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import javax.swing.Icon

abstract class SvModuleDeclarationMixin(node: ASTNode) : SvModuleDeclaration, SvNameIdentifierOwnerImpl(node),
    SvReferenceElement
{
    // -- SvNameIdentifierOwner
    override fun getNameIdentifier(): PsiElement = identifier

    override fun getPresentation(): ItemPresentation
    {
        return object : ItemPresentation  // TODO: Use more advanced PresentationData, see ItemPresentation doc
        {
            override fun getPresentableText(): String? = identifier.text
            override fun getLocationString(): String = containingFile.name
            override fun getIcon(unused: Boolean): Icon = SvIcons.SV_FILE
        }
    }

    // -- SvReferenceElement
    override val referenceElement: PsiElement? get() = endmoduleLabel

    override fun getReference(): PsiReference?
    {
        if (endmoduleLabel == null) return null
        return object : SvMonoReference<SvModuleDeclaration>(this, endmoduleLabel!!.textRangeInParent) {
            override fun resolve(): PsiElement = identifier
        }
    }
}