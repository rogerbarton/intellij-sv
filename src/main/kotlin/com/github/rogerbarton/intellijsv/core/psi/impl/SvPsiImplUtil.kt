package com.github.rogerbarton.intellijsv.core.psi.impl

import com.github.rogerbarton.intellijsv.core.psi.SvElementFactory
import com.github.rogerbarton.intellijsv.core.psi.SvModuleDeclaration
import com.github.rogerbarton.intellijsv.core.psi.SvTypes
import com.github.rogerbarton.intellijsv.core.psi.ext.getIdentifier
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

class SvPsiImplUtil {
    companion object {
        @JvmStatic
        fun getName(element: SvModuleDeclaration): String? = element.getIdentifier().text

        @JvmStatic
        fun getNameIdentifier(element: SvModuleDeclaration): PsiElement = element.getIdentifier()

        @JvmStatic
        fun setName(element: SvModuleDeclaration, newName: String): PsiElement
        {
            val idNode: ASTNode = element.node.findChildByType(SvTypes.IDENTIFIER) ?: return element

            val module: SvModuleDeclaration = SvElementFactory.createModuleDeclaration(element.project, newName)
            val newIdNode: ASTNode = module.firstChild.node
            element.node.replaceChild(idNode, newIdNode)

            return element
        }
    }
}