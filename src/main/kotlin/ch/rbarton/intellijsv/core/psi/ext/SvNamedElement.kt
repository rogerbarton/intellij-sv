package ch.rbarton.intellijsv.core.psi.ext

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.PsiNamedElement

interface SvNamedElement : PsiNameIdentifierOwner

abstract class SvNamedElementImpl(node: ASTNode) : ASTWrapperPsiElement(node), SvNamedElement