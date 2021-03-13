package com.github.rogerbarton.intellijsv.psi;

import com.github.rogerbarton.intellijsv.SvFileType;
import com.github.rogerbarton.intellijsv.SvLanguage;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class SvFile extends PsiFileBase {
    public SvFile(@NotNull FileViewProvider viewProvider)
    {
        super(viewProvider, SvLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return SvFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "System Verilog File";
    }
}
