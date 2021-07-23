package ch.rbarton.intellijsv.ide.doc

import ch.rbarton.intellijsv.core.psi.SvModuleDeclaration
import ch.rbarton.intellijsv.core.psi.SvNetDeclarationAssignment
import ch.rbarton.intellijsv.core.psi.SvParameterDeclaration
import ch.rbarton.intellijsv.core.psi.SvPortDeclaration
import com.intellij.lang.documentation.AbstractDocumentationProvider
import com.intellij.psi.PsiElement

class SvDocumentationProvider : AbstractDocumentationProvider()
{
    override fun generateDoc(element: PsiElement?, originalElement: PsiElement?): String?
    {
        return super.getQuickNavigateInfo(element, originalElement)
    }

    override fun getQuickNavigateInfo(element: PsiElement?, originalElement: PsiElement?): String? =
        when (element)
        {
            is SvModuleDeclaration -> "module <b>${element.identifier.text}</b>"
            is SvParameterDeclaration -> "${element.parameterType.text} ${element.type?.text} <b>${element.identifier?.text}</b>"
            is SvPortDeclaration -> "${element.portDirection.text} ${element.type?.text} <b>${element.identifier?.text}</b>"
            is SvNetDeclarationAssignment -> "net ${element.identifier}"
            else -> null
        }
}
