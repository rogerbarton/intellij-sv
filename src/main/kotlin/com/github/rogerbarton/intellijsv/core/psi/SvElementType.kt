package com.github.rogerbarton.intellijsv.core.psi

import org.jetbrains.annotations.NonNls
import com.intellij.psi.tree.IElementType

class SvElementType(@NonNls debugName: String) : IElementType(debugName, SvLanguage.INSTANCE)