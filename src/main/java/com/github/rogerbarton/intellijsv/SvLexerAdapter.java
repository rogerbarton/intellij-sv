package com.github.rogerbarton.intellijsv;

import com.intellij.lexer.FlexAdapter;

public class SvLexerAdapter extends FlexAdapter {
    public SvLexerAdapter() {
        super(new SvLexer(null));
    }
}
