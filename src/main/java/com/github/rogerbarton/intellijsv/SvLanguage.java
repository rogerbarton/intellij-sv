package com.github.rogerbarton.intellijsv;

import com.intellij.lang.Language;

public class SvLanguage extends Language {
    public static final SvLanguage INSTANCE = new SvLanguage();

    private SvLanguage() {
        super("Sv");
    }
}
