package ch.rbarton.intellijsv.ide.search

import ch.rbarton.intellijsv.core.psi.*
import ch.rbarton.intellijsv.core.psi.ext.SvNamedIdentifierOwner
import com.intellij.lang.HelpID
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.psi.PsiElement

class SvFindUsagesProvider : FindUsagesProvider
{
    override fun getWordsScanner() = SvWordScanner()

    override fun canFindUsagesFor(element: PsiElement) = element is SvNamedIdentifierOwner

    override fun getHelpId(element: PsiElement) = HelpID.FIND_OTHER_USAGES

    /**
     * Appears in Find Usages window next to the item we are acting on
     */
    override fun getType(element: PsiElement): String = when (element)
    {
        // TODO: Calculate this properly
        is SvModuleDeclaration -> "module"
        is SvParameterDeclaration -> "parameter"
        is SvPortDeclaration -> "module port"
        is SvNetDeclaration -> "net"
        is SvTypeDeclaration -> "typedef"
        is SvModuleInstantiation -> "module instance"
        else -> ""
    }

    override fun getDescriptiveName(element: PsiElement): String = (element as? SvNamedIdentifierOwner)?.name.orEmpty()
    override fun getNodeText(element: PsiElement, useFullName: Boolean): String = ""
}