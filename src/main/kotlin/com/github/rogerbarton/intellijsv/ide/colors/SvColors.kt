package com.github.rogerbarton.intellijsv.ide.colors

import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors as Default

enum class SvColor(humanName: String, default: TextAttributesKey? = null) {
    IDENTIFIER("General//Identifier", Default.IDENTIFIER),
    NUMBER("General//Number", Default.NUMBER),

    KEYWORD("Keywords and Types//Keyword", Default.KEYWORD),
    BUILTIN_TYPE("Keywords and Types//Type", Default.KEYWORD),
    SIGNING("Keywords and Types//Signing", Default.KEYWORD),
    PORT_DIRECTION("Keywords and Types//Port direction", Default.KEYWORD),


    OPERATOR("Braces and Operators//Operator", Default.OPERATION_SIGN),
    BRACES("Braces and Operators//Braces", Default.BRACES),
    BRACKETS("Braces and Operators//Brackets", Default.BRACKETS),
    PARENTHESES("Braces and Operators//Parentheses", Default.PARENTHESES),
    SEMICOLON("Braces and Operators//Semicolon", Default.SEMICOLON),
    DOT("Braces and Operators//Dot", Default.DOT),
    COMMA("Braces and Operators//Comma", Default.COMMA),

    LINE_COMMENT("Comments//Line comment", Default.LINE_COMMENT),
    BLOCK_COMMENT("Comments//Block comment", Default.BLOCK_COMMENT),

    BAD_CHARACTER("Error//Bad character", HighlighterColors.BAD_CHARACTER),
    ;

    val textAttributesKey = TextAttributesKey.createTextAttributesKey("sv.$name", default)
    val attributesDescriptor = AttributesDescriptor(humanName, textAttributesKey)
    val testSeverity: HighlightSeverity = HighlightSeverity(name, HighlightSeverity.INFORMATION.myVal) 
}