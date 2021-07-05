package com.github.rogerbarton.intellijsv.core.psi.ext

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiNamedElement

public interface SvNamedElement :PsiNamedElement
{
}

abstract class SvNamedElementImpl(node: ASTNode) : ASTWrapperPsiElement(node), SvNamedElement