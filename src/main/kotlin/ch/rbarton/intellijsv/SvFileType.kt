package ch.rbarton.intellijsv

import ch.rbarton.intellijsv.ide.SvIcons
import com.intellij.openapi.fileTypes.LanguageFileType

class SvFileType : LanguageFileType(SvLanguage)
{
    override fun getName() = "System Verilog"

    override fun getIcon() = SvIcons.SV_FILE

    override fun getDefaultExtension() = "sv"

    @Suppress("DialogTitleCapitalization")
    override fun getDescription() = "System Verilog HDL File"

    companion object
    {
        val INSTANCE: SvFileType = SvFileType()
    }
}