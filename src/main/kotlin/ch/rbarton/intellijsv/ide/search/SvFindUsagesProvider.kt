package ch.rbarton.intellijsv.ide.search

import ch.rbarton.intellijsv.core.psi.ext.SvNamedIdentifierOwner
import com.intellij.lang.HelpID
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.psi.PsiElement

class SvFindUsagesProvider : FindUsagesProvider
{
    override fun getWordsScanner() = SvWordScanner()

    override fun canFindUsagesFor(element: PsiElement) = element is SvNamedIdentifierOwner

    override fun getHelpId(element: PsiElement) = HelpID.FIND_OTHER_USAGES

    override fun getType(element: PsiElement) = ""
    override fun getDescriptiveName(element: PsiElement) = (element as? SvNamedIdentifierOwner)?.name.orEmpty()
    override fun getNodeText(element: PsiElement, useFullName: Boolean) = ""
}