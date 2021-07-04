package com.github.rogerbarton.intellijsv.core

import com.github.rogerbarton.intellijsv.core.lexer.SvLexer
import com.github.rogerbarton.intellijsv.ide.colors.SvColor
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.github.rogerbarton.intellijsv.psi.SvTypes
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.psi.TokenType

class SvSyntaxHighlighter : SyntaxHighlighterBase()
{
    override fun getHighlightingLexer(): Lexer = SvLexer()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> =
        pack(map(tokenType)?.textAttributesKey)

    companion object {
        fun map(tokenType: IElementType): SvColor? = when (tokenType)
        {
            SvTypes.TIMEUNIT, SvTypes.TIMEPRECISION -> SvColor.KEYWORD

            SvTypes.LINE_COMMENT -> SvColor.LINE_COMMENT
            SvTypes.BLOCK_COMMENT -> SvColor.BLOCK_COMMENT

            TokenType.BAD_CHARACTER -> SvColor.BAD_CHARACTER

            else -> null
        }
    }
}