package com.github.rogerbarton.intellijsv.core.psi

import com.github.rogerbarton.intellijsv.SvFileType
import com.github.rogerbarton.intellijsv.SvLanguage
import com.intellij.psi.FileViewProvider
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType

class SvFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, SvLanguage)
{
    override fun getFileType(): FileType = SvFileType()

    override fun toString() = "System Verilog File"
}