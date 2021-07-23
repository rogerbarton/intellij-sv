package ch.rbarton.intellijsv.core.psi.mixin

import ch.rbarton.intellijsv.core.psi.SvModuleDeclaration
import ch.rbarton.intellijsv.core.psi.ext.SvNamedIdentifierOwnerImpl
import ch.rbarton.intellijsv.core.resolve.SvMonoReference
import ch.rbarton.intellijsv.ide.SvIcons
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import javax.swing.Icon

abstract class SvModuleDeclarationMixin(node: ASTNode) : SvModuleDeclaration, SvNamedIdentifierOwnerImpl(node)
{
    override fun getIcon(flags: Int): Icon? = SvIcons.SV_MODULE

    // -- SvNameIdentifierOwner
    override fun getNameIdentifier(): PsiElement = identifier

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