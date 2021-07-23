package ch.rbarton.intellijsv.core.parser

import com.intellij.lang.PsiBuilder
import com.intellij.lang.parser.GeneratedParserUtilBase
import com.intellij.openapi.util.Key
import com.intellij.util.BitUtil
import com.intellij.util.containers.Stack

/**
 * Provides special <<meta rules>> for BNF.
 * Stores some more advanced state in flags, e.g. for const expressions.
 */
@Suppress("UNUSED_PARAMETER")
object SvParserUtil : GeneratedParserUtilBase()
{
    private val IS_CONST_EXPR: Int = makeBitMask(0)

    private const val DEFAULT_FLAGS: Int = 0
    private val FLAGS: Key<Int> = Key("SvParserUtil.FLAGS")
    private var PsiBuilder.flags: Int
        get() = getUserData(FLAGS) ?: DEFAULT_FLAGS
        set(value) = putUserData(FLAGS, value)

    private val FLAG_STACK: Key<Stack<Int>> = Key("RustParserUtil.FLAG_STACK")
    private var PsiBuilder.flagStack: Stack<Int>
        get() = getUserData(FLAG_STACK) ?: Stack<Int>(0)
        set(value) = putUserData(FLAG_STACK, value)

    //region State Modification
    /**
     * Parse the provided rule with a specific expression type, e.g. const
     */
    @JvmStatic
    fun exprMode(
        builder: PsiBuilder,
        level: Int,
        isConst: Boolean,
        parser: Parser
    ): Boolean
    {
        val oldFlags = builder.flags
        val newFlags = oldFlags.setFlag(IS_CONST_EXPR, isConst)
        builder.flags = newFlags
        val result = parser.parse(builder, level)
        builder.flags = oldFlags
        return result
    }

    /**
     * Manual approach of [exprMode] using begin and end.
     */
    @JvmStatic
    fun beginConstExpr(b: PsiBuilder, level: Int): Boolean {
        pushFlag(b, level, IS_CONST_EXPR, true)
        return true
    }

    @JvmStatic
    fun endConstExpr(b: PsiBuilder, level: Int): Boolean {
        popFlag(b, level)
        return true
    }

    @JvmStatic
    fun pushFlag(b: PsiBuilder, level: Int, flag: Int, value: Boolean): Boolean {
        b.pushFlag(flag, value)
        return true
    }

    @JvmStatic
    fun popFlag(b: PsiBuilder, level: Int): Boolean {
        b.popFlag()
        return true
    }
    //endregion

    //region Predicates
    @JvmStatic
    fun isConstExpr(b: PsiBuilder, level: Int): Boolean = BitUtil.isSet(b.flags, IS_CONST_EXPR)

    @JvmStatic
    fun isNonConstExpr(b: PsiBuilder, level: Int): Boolean = !BitUtil.isSet(b.flags, IS_CONST_EXPR)
    //endregion

    //region Helpers
    private fun PsiBuilder.pushFlag(flag: Int, mode: Boolean) {
        val stack = flagStack
        stack.push(flags)
        flagStack = stack
        flags = BitUtil.set(flags, flag, mode)
    }

    private fun PsiBuilder.popFlag() {
        flags = flagStack.pop()
    }

    private fun Int.setFlag(flag: Int, mode: Boolean): Int =
        BitUtil.set(this, flag, mode)

    private fun makeBitMask(bitToSet: Int): Int = 1 shl bitToSet
    //endregion
}

