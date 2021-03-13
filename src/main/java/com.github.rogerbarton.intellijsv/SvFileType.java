package com.github.rogerbarton.intellijsv;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class SvFileType extends LanguageFileType {
    public static final SvFileType INSTANCE = new SvFileType();

    private SvFileType() {
        super(SvLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "System Verilog File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "System Verilog (HDL) File";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "sv";
    }

    @NotNull
    @Override
    public Icon getIcon() {
        return SvIcons.FILE;
    }
}
