package com.github.rogerbarton.intellijsv.ide.colors

import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors as Default

enum class SvColor(humanName: String, default: TextAttributesKey? = null) {
    KEYWORD("Keywords//Keyword", Default.KEYWORD),
    
    LINE_COMMENT("Comments//Line comment", Default.LINE_COMMENT),
    BLOCK_COMMENT("Comments//Block comment", Default.BLOCK_COMMENT),

    BAD_CHARACTER("Error//Bad character", HighlighterColors.BAD_CHARACTER),
    ;

    val textAttributesKey = TextAttributesKey.createTextAttributesKey("sv.$name", default)
    val attributesDescriptor = AttributesDescriptor(humanName, textAttributesKey)
    val testSeverity: HighlightSeverity = HighlightSeverity(name, HighlightSeverity.INFORMATION.myVal) 
}