package com.github.rogerbarton.intellijsv.psi

import org.jetbrains.annotations.NonNls
import com.intellij.psi.tree.IElementType

class SvTokenType(@NonNls debugName: String) : IElementType(debugName, SvLanguage.INSTANCE)
{
    override fun toString(): String
    {
        return "SvTokenType." + super.toString()
    }
}