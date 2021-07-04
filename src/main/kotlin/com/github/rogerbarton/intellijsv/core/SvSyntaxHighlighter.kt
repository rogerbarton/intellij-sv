package com.github.rogerbarton.intellijsv.core

import com.github.rogerbarton.intellijsv.core.lexer.SvLexer
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
    companion object
    {
        val VALUE = TextAttributesKey.createTextAttributesKey("SV_VALUE", DefaultLanguageHighlighterColors.STRING)
        val LINE_COMMENT =
            TextAttributesKey.createTextAttributesKey("SV_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val BLOCK_COMMENT =
            TextAttributesKey.createTextAttributesKey("SV_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT)
        val BAD_CHARACTER =
            TextAttributesKey.createTextAttributesKey("SV_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

        private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
        private val VALUE_KEYS = arrayOf(VALUE)
        private val LINE_COMMENT_KEYS = arrayOf(LINE_COMMENT)
        private val BLOCK_COMMENT_KEYS = arrayOf(BLOCK_COMMENT)
        private val EMPTY_KEYS = arrayOf<TextAttributesKey>()
    }

    override fun getHighlightingLexer(): Lexer = SvLexer()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey>
    {
        return when (tokenType)
        {
            SvTypes.LINE_COMMENT -> LINE_COMMENT_KEYS
            SvTypes.BLOCK_COMMENT -> BLOCK_COMMENT_KEYS
            TokenType.BAD_CHARACTER -> BAD_CHAR_KEYS
            else -> EMPTY_KEYS
        }
    }
}