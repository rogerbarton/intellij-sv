package com.github.rogerbarton.intellijsv.core.psi.ext

import com.github.rogerbarton.intellijsv.core.psi.*
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.PsiTreeUtil.getChildrenOfTypeAsList
import org.jetbrains.annotations.NotNull

fun SvModuleDeclaration.getIdentifierList(): List<PsiElement>
{
    return node.getChildren(tokenSetOf(SvTypes.IDENTIFIER)).map { it.psi }
}

fun SvModuleDeclaration.getIdentifier(): PsiElement
{
    return getIdentifierList()[0]
}

fun SvModuleDeclaration.getIdentifierPostfix(): PsiElement?
{
    val ids = getIdentifierList()
    return if (ids.size < 2) null else ids[1]
}