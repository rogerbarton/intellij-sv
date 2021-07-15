package ch.rbarton.intellijsv.core.psi

import ch.rbarton.intellijsv.SvLanguage
import org.jetbrains.annotations.NonNls
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet
import ch.rbarton.intellijsv.core.psi.SvTypes.*

class SvTokenType(@NonNls debugName: String) : IElementType(debugName, SvLanguage)
{
    override fun toString() = "SvTokenType." + super.toString()
}

fun tokenSetOf(vararg tokens: IElementType) = TokenSet.create(*tokens)

val SV_KEYWORDS = tokenSetOf(
    TIMEUNIT, TIMEPRECISION,
    MODULE, ENDMODULE,
    PARAMETER, LOCALPARAM
)

val SV_PORT_DIRECTION = tokenSetOf(
    INPUT, OUTPUT, INOUT, REF
)

val SV_BUILTIN_TYPES = tokenSetOf(
    INT, LOGIC, REG, WIRE,
)

val SV_OPERATORS = tokenSetOf(
    COLON, COLONCOLON, COMMA, EQ, DIV, SHA, DOT,
)

val SV_NUMBERS = tokenSetOf(
    BINARY_NUMBER, OCTAL_NUMBER, HEX_NUMBER, UNSIGNED_NUMBER,
)

val SV_COMMENTS = tokenSetOf(LINE_COMMENT, BLOCK_COMMENT, DOC_COMMENT)

val SV_STRING_LITERALS = tokenSetOf(STRING_LITERAL)