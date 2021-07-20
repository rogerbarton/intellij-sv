package ch.rbarton.intellijsv.core.psi.ext

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference

/**
 * Describes an identifier than references to a declaration, e.g. a module instantiation.
 */
interface SvReferenceElement : PsiElement
{
    val referenceElement: PsiElement?
    override fun getReference(): PsiReference?
}