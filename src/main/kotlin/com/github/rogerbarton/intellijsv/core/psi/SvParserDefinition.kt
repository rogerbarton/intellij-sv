package com.github.rogerbarton.intellijsv.core.psi

import com.github.rogerbarton.intellijsv.SvLanguage
import com.github.rogerbarton.intellijsv.core.lexer.SvLexer
import com.intellij.lang.ParserDefinition
import com.github.rogerbarton.intellijsv.parser.SvParser
import com.github.rogerbarton.intellijsv.psi.SvTypes
import com.intellij.psi.tree.TokenSet
import com.intellij.lang.ASTNode
import com.intellij.lang.PsiParser
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiFile
import com.intellij.lang.ParserDefinition.SpaceRequirements
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.TokenType

class SvParserDefinition : ParserDefinition
{
    override fun createLexer(project: Project): Lexer = SvLexer()

    override fun getWhitespaceTokens(): TokenSet = WHITE_SPACES

    override fun getCommentTokens(): TokenSet = COMMENTS

    override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY

    override fun createParser(project: Project): PsiParser = SvParser()

    override fun getFileNodeType(): IFileElementType = FILE

    override fun createFile(viewProvider: FileViewProvider): PsiFile = SvFile(viewProvider)

    override fun spaceExistenceTypeBetweenTokens(left: ASTNode, right: ASTNode) = SpaceRequirements.MAY

    override fun createElement(node: ASTNode): PsiElement = SvTypes.Factory.createElement(node)

    companion object
    {
        val FILE: IFileElementType = IFileElementType(SvLanguage);
        val WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE)
        val COMMENTS = TokenSet.create(SvTypes.LINE_COMMENT)
    }
}