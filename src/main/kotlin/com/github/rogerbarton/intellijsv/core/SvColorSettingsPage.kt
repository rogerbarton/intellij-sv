package com.github.rogerbarton.intellijsv

import com.intellij.openapi.options.colors.ColorSettingsPage
import com.github.rogerbarton.intellijsv.core.SvIcons
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.github.rogerbarton.intellijsv.SvColorSettingsPage
import com.intellij.openapi.options.colors.ColorDescriptor
import javax.swing.Icon

class SvColorSettingsPage : ColorSettingsPage
{
    override fun getIcon(): Icon?
    {
        return SvIcons.SV_FILE
    }

    override fun getHighlighter(): SyntaxHighlighter
    {
        return SvSyntaxHighlighter()
    }

    override fun getDemoText(): String
    {
        return """# You are reading the ".properties" entry.
! The exclamation mark can also mark text as comments.
website = https://en.wikipedia.org/
language = English
# The backslash below tells the application to continue reading
# the value onto the next line.
message = Welcome to \
          Wikipedia!
# Add spaces to the key
key\ with\ spaces = This is the value that could be looked up with the key "key with spaces".
# Unicode
tab : \u0009"""
    }

    override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey>?
    {
        return null
    }

    override fun getAttributeDescriptors(): Array<AttributesDescriptor>
    {
        return DESCRIPTORS
    }

    override fun getColorDescriptors(): Array<ColorDescriptor>
    {
        return ColorDescriptor.EMPTY_ARRAY
    }

    override fun getDisplayName(): String
    {
        return "System Verilog"
    }

    companion object
    {
        private val DESCRIPTORS = arrayOf(
            AttributesDescriptor("Key", SvSyntaxHighlighter.KEY),
            AttributesDescriptor("Separator", SvSyntaxHighlighter.SEPARATOR),
            AttributesDescriptor("Value", SvSyntaxHighlighter.VALUE),
            AttributesDescriptor("Bad Value", SvSyntaxHighlighter.BAD_CHARACTER)
        )
    }
}