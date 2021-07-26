package ch.rbarton.intellijsv.core.psi.ext

import ch.rbarton.intellijsv.core.psi.SvPsiFactory
import ch.rbarton.intellijsv.core.psi.SvTypes.IDENTIFIER
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.ide.projectView.PresentationData
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner

/**
 * More advanced NamedElement, use this when an identifier is declared, e.g. a new class name.
 * The parent rule of the identifier token should usually be the NamedIdentifierOwner.
 */
interface SvNamedIdentifierOwner : PsiNameIdentifierOwner, NavigatablePsiElement

abstract class SvNamedIdentifierOwnerImpl(node: ASTNode) : SvNamedIdentifierOwner, ASTWrapperPsiElement(node)
{
    /**
     * Token for the newly declared identifier
     */
    override fun getNameIdentifier(): PsiElement? = findChildByType(IDENTIFIER)

    override fun getName(): String? = nameIdentifier?.text

    override fun setName(name: String): PsiElement?
    {
        nameIdentifier?.replace(SvPsiFactory(project).createIdentifier(name))
        return this
    }

    /**
     * Important: this converts the a reference resolving to the owning rule (IdentifierOwner)
     *            to the actual identifier token.
     */
    override fun getTextOffset(): Int = nameIdentifier?.textOffset ?: super.getTextOffset()

    override fun getPresentation(): ItemPresentation =
        PresentationData(name, containingFile.name, getIcon(0), null)
}
