package ch.rbarton.intellijsv.core

import ch.rbarton.intellijsv.core.lexer.SvLexer
import ch.rbarton.intellijsv.ide.colors.SvColor
import ch.rbarton.intellijsv.core.psi.SvTypes.*
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import ch.rbarton.intellijsv.core.psi.*

class SvSyntaxHighlighter : SyntaxHighlighterBase()
{
    override fun getHighlightingLexer(): Lexer = SvLexer()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> =
        pack(map(tokenType)?.textAttributesKey)

    companion object {
        fun map(tokenType: IElementType): SvColor? = when (tokenType)
        {
            IDENTIFIER -> SvColor.IDENTIFIER

            in SV_KEYWORDS -> SvColor.KEYWORD
            in SV_BUILTIN_TYPES -> SvColor.BUILTIN_TYPE
            SIGNED, UNSIGNED -> SvColor.SIGNING
            in SV_PORT_DIRECTION -> SvColor.PORT_DIRECTION

            in SV_NUMBERS -> SvColor.NUMBER
            LBRACE, RBRACE -> SvColor.BRACES
            LBRACK, RBRACK -> SvColor.BRACKETS
            LPAREN, RPAREN -> SvColor.PARENTHESES

            SEMICOLON -> SvColor.SEMICOLON
            DOT -> SvColor.DOT
            COMMA -> SvColor.COMMA
            in SV_OPERATORS -> SvColor.OPERATOR

            LINE_COMMENT -> SvColor.LINE_COMMENT
            BLOCK_COMMENT -> SvColor.BLOCK_COMMENT

            TokenType.BAD_CHARACTER -> SvColor.BAD_CHARACTER

            else -> null
        }
    }
}