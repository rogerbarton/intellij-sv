package ch.rbarton.intellijsv.ide.colors

import ch.rbarton.intellijsv.ide.SvIcons
import ch.rbarton.intellijsv.ide.SvSyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import com.intellij.openapi.util.io.StreamUtil
import java.nio.charset.Charset

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
                .getResourceAsStream("ch.rbarton.intellijsv.ide/colors/HighlighterDemo.sv")
                ?: return@lazy "Failed to find demo file."
            StreamUtil.convertSeparators(StreamUtil.readText(stream.reader(Charset.defaultCharset())))
        }
    }
}