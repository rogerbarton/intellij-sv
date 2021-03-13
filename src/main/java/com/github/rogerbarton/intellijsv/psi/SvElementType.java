package com.github.rogerbarton.intellijsv.psi;

import com.github.rogerbarton.intellijsv.SvLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class SvElementType extends IElementType {

    public SvElementType(@NotNull @NonNls String debugName) {
        super(debugName, SvLanguage.INSTANCE);
    }
}
