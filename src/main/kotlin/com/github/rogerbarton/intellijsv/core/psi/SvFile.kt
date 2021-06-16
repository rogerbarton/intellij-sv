package com.github.rogerbarton.intellijsv.psi

import com.intellij.psi.FileViewProvider
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType

class SvFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, SvLanguage.INSTANCE)
{
    override fun getFileType(): FileType
    {
        return SvFileType.INSTANCE
    }

    override fun toString(): String
    {
        return "System Verilog File"
    }
}