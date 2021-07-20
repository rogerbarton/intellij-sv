package ch.rbarton.intellijsv.ide.refactor

import ch.rbarton.intellijsv.core.psi.ext.SvNamedIdentifierOwner
import com.intellij.lang.refactoring.RefactoringSupportProvider
import com.intellij.psi.PsiElement

class SvRefactoringSupportProvider : RefactoringSupportProvider()
{
    override fun isMemberInplaceRenameAvailable(element: PsiElement, context: PsiElement?) =
        element is SvNamedIdentifierOwner
}