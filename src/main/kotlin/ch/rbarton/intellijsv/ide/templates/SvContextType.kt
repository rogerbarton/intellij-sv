package ch.rbarton.intellijsv.ide.templates

import ch.rbarton.intellijsv.SvLanguage
import ch.rbarton.intellijsv.core.psi.SvExpr
import ch.rbarton.intellijsv.core.psi.SvModuleDeclaration
import ch.rbarton.intellijsv.core.psi.SvParameterDeclaration
import ch.rbarton.intellijsv.core.psi.SvPortDeclaration
import ch.rbarton.intellijsv.ide.SvSyntaxHighlighter
import com.intellij.codeInsight.template.EverywhereContextType
import com.intellij.codeInsight.template.TemplateActionContext
import com.intellij.codeInsight.template.TemplateContextType
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.PsiUtilCore
import kotlin.reflect.KClass

sealed class SvContextType(
    id: String,
    presentableName: String,
    baseContextType: KClass<out TemplateContextType>
) : TemplateContextType(id, presentableName, baseContextType.java)
{
    final override fun isInContext(context: TemplateActionContext): Boolean
    {
        if (!PsiUtilCore.getLanguageAtOffset(context.file, context.startOffset).isKindOf(SvLanguage)) return false

        val element = context.file.findElementAt(context.startOffset)
        if (element == null || element is PsiComment) return false

        return isInContext(element)
    }

    protected abstract fun isInContext(element: PsiElement): Boolean

    override fun createHighlighter(): SyntaxHighlighter? = SvSyntaxHighlighter()

    class Generic : SvContextType("SV_FILE", "System Verilog", EverywhereContextType::class)
    {
        override fun isInContext(element: PsiElement): Boolean = true
    }

    class Module : SvContextType("MODULE", "Module", Generic::class)
    {
        override fun isInContext(element: PsiElement): Boolean = owner(element) is SvModuleDeclaration
    }

    companion object
    {
        private fun owner(element: PsiElement): PsiElement? = PsiTreeUtil.findFirstParent(element) {
            it is SvModuleDeclaration || it is SvPortDeclaration || it is SvParameterDeclaration || it is SvExpr
        }
    }
}