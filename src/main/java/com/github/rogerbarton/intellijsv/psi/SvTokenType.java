package com.github.rogerbarton.intellijsv.psi;

import com.github.rogerbarton.intellijsv.SvLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class SvTokenType extends IElementType {
    public SvTokenType(@NotNull @NonNls String debugName) {
        super(debugName, SvLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "SvTokenType." + super.toString();
    }
}
