package com.github.rogerbarton.intellijsv

import com.intellij.lang.Language

object SvLanguage : Language("System Verilog", "text/sv", "text/x-sv", "application/x-sv")
{
    override fun isCaseSensitive() = true

    override fun getDisplayName() = "System Verilog"
}