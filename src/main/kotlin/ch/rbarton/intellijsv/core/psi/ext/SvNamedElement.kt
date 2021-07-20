package ch.rbarton.intellijsv.core.psi.ext

import ch.rbarton.intellijsv.core.psi.SvPsiFactory
import ch.rbarton.intellijsv.core.psi.SvTypes.IDENTIFIER
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.PsiNamedElement

interface SvNamedElement : PsiNamedElement, NavigatablePsiElement

interface SvNamedIdentifierOwner : SvNamedElement, PsiNameIdentifierOwner

abstract class SvNamedElementImpl(node: ASTNode) : SvNamedIdentifierOwner, ASTWrapperPsiElement(node) {

    override fun getNameIdentifier(): PsiElement? = findChildByType(IDENTIFIER)

    override fun getName(): String? = nameIdentifier?.text

    override fun setName(name: String): PsiElement? {
        nameIdentifier?.replace(SvPsiFactory(project).createIdentifier(name))
        return this
    }

    override fun getTextOffset(): Int = nameIdentifier?.textOffset ?: super.getTextOffset()
}
