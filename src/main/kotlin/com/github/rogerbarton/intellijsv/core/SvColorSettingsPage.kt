package com.github.rogerbarton.intellijsv.core

import com.intellij.openapi.options.colors.ColorSettingsPage
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import javax.swing.Icon

class SvColorSettingsPage : ColorSettingsPage
{
    override fun getDisplayName() = "System Verilog"

    override fun getIcon() = SvIcons.SV_FILE

    override fun getHighlighter() = SvSyntaxHighlighter()

    override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey>? = null

    override fun getAttributeDescriptors(): Array<AttributesDescriptor> = DESCRIPTORS

    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    companion object
    {
        private val DESCRIPTORS = arrayOf(
            AttributesDescriptor("Value", SvSyntaxHighlighter.VALUE),
            AttributesDescriptor("Line Comment", SvSyntaxHighlighter.LINE_COMMENT),
            AttributesDescriptor("Block Comment", SvSyntaxHighlighter.BLOCK_COMMENT),
            AttributesDescriptor("Bad Value", SvSyntaxHighlighter.BAD_CHARACTER)
        )
    }

    override fun getDemoText(): String
    {
        return """timeunit 1us;
timeprecision 1ps;

module filter #(
//    parameter int unsigned Order      = 127, // Filter order
//    parameter int unsigned AddrWidth  = 7   // Address width
  ) (
    input logic clk_i,                         // Clock signal

    input logic                 data_in_req_i, // Req at input
    input logic [DataWidth-1:0] data_in_i,     // Incoming data
  );

endmodule : filter"""
    }
}