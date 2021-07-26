package ch.rbarton.intellijsv.core.psi.ext

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference

/**
 * Describes an object that contains a reference to a declaration, e.g. a module instantiation, not the identifier token which.
 * The parent rule of the identifier token should usually be the ReferenceOwner.
 */
interface SvReferenceOwner : PsiElement
{
    /**
     * The Identifier child of the object which owns the reference
     */
    val referenceIdentifier: PsiElement?

    /**
     * Should return a [PsiReference] where:
     * - [PsiReference.resolve()] returns the IdentifierOwner object, not the Identifier itself.
     *   The IdentifierOwner already knows the TextOffset of the Identifier
     * - [PsiReference.isReferenceTo()] should expect an IdentifierOwner and check that it is the one being referenced.
     */
    override fun getReference(): PsiReference?
}