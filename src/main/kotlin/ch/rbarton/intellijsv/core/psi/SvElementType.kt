package ch.rbarton.intellijsv.core.psi

import ch.rbarton.intellijsv.SvLanguage
import org.jetbrains.annotations.NonNls
import com.intellij.psi.tree.IElementType

class SvElementType(debugName: String) : IElementType(debugName, SvLanguage)