package com.github.rogerbarton.intellijsv.ide.colors

import com.github.rogerbarton.intellijsv.core.SvIcons
import com.github.rogerbarton.intellijsv.core.SvSyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import com.intellij.openapi.util.io.StreamUtil

class SvColorSettingsPage : ColorSettingsPage
{
    override fun getDisplayName() = "System Verilog"
    override fun getIcon() = SvIcons.SV_FILE
    override fun getAttributeDescriptors(): Array<AttributesDescriptor> = ATTRS
    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY
    override fun getHighlighter() = SvSyntaxHighlighter()
    override fun getAdditionalHighlightingTagToDescriptorMap() = ANNOTATOR_TAGS
    override fun getDemoText() = DEMO_TEXT

    companion object
    {
        private val ATTRS = SvColor.values().map { it.attributesDescriptor }.toTypedArray()

        private val ANNOTATOR_TAGS = SvColor.values().associateBy({ it.name }, { it.textAttributesKey })

        private val DEMO_TEXT: String by lazy {
            val stream = SvColorSettingsPage::class.java.classLoader
                .getResourceAsStream("com.github.rogerbarton.intellij.ide/colors/HighlighterDemo.sv")
            StreamUtil.convertSeparators(StreamUtil.readText(stream, "UTF-8"))
        }
    }
}