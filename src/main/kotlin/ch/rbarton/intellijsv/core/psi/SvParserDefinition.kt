package ch.rbarton.intellijsv.core.psi

import ch.rbarton.intellijsv.SvLanguage
import ch.rbarton.intellijsv.core.lexer.SvLexer
import com.intellij.lang.ParserDefinition
import ch.rbarton.intellijsv.core.parser.SvParser
import ch.rbarton.intellijsv.core.psi.SvTypes.*
import com.intellij.psi.tree.TokenSet
import com.intellij.lang.ASTNode
import com.intellij.lang.LanguageUtil
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
    override fun createParser(project: Project): PsiParser = SvParser()
    override fun createFile(viewProvider: FileViewProvider): PsiFile = SvFile(viewProvider)

    override fun getWhitespaceTokens(): TokenSet = TokenSet.create(TokenType.WHITE_SPACE)
    override fun getCommentTokens(): TokenSet = SV_COMMENTS
    override fun getStringLiteralElements(): TokenSet = SV_STRING_LITERALS

    override fun getFileNodeType(): IFileElementType = FILE

    override fun spaceExistenceTypeBetweenTokens(left: ASTNode, right: ASTNode): SpaceRequirements
    {
        // Make Lexer check if a space is required or not
        return if (left.elementType == LINE_COMMENT)
            SpaceRequirements.MUST_LINE_BREAK
        else
            LanguageUtil.canStickTokensTogetherByLexer(left, right, SvLexer())
    }

    override fun createElement(node: ASTNode): PsiElement = Factory.createElement(node)

    companion object
    {
        val FILE: IFileElementType = IFileElementType(SvLanguage)
    }
}