package ch.rbarton.intellijsv.core.psi

import ch.rbarton.intellijsv.core.SvIcons
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*

class SvReference(element: PsiElement, textRange: TextRange) : PsiReferenceBase<PsiElement>(element, textRange),
    PsiPolyVariantReference
{
    private val id: String = element.text.substring(textRange.startOffset, textRange.endOffset)

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult>
    {
        return SvUtil.findModuleIdentifiers(myElement.project, id).map { PsiElementResolveResult(it) }.toTypedArray()
    }

    override fun resolve(): PsiElement?
    {
        val resolveResults = multiResolve(false)
        return if (resolveResults.size == 1) resolveResults[0].element else null
    }

    override fun getVariants(): Array<Any>
    {
        val variants: MutableList<LookupElement> = mutableListOf()
        SvUtil.findModuleIdentifiers(myElement.project).forEach{
            variants += LookupElementBuilder.create(it).withIcon(SvIcons.SV_FILE).withTypeText(it.containingFile.text)
        }
        return variants.toTypedArray()
    }
}