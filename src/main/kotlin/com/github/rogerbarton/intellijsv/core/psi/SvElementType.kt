package com.github.rogerbarton.intellijsv.core.psi

import com.github.rogerbarton.intellijsv.SvLanguage
import org.jetbrains.annotations.NonNls
import com.intellij.psi.tree.IElementType

class SvElementType(debugName: String) : IElementType(debugName, SvLanguage)