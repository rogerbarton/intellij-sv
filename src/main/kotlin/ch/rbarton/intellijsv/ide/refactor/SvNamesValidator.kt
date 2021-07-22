package ch.rbarton.intellijsv.ide.refactor

import ch.rbarton.intellijsv.core.lexer.SvLexer
import ch.rbarton.intellijsv.core.psi.SV_KEYWORDS
import ch.rbarton.intellijsv.core.psi.SvTypes.*
import com.intellij.lang.refactoring.NamesValidator
import com.intellij.openapi.project.Project
import com.intellij.psi.tree.IElementType

/**
 * Note: prefer this to more advanced RenameInputValidator as its universal
 */
class SvNamesValidator : NamesValidator
{
    override fun isKeyword(name: String, project: Project?): Boolean = isKeyword(name)

    override fun isIdentifier(name: String, project: Project?): Boolean = isIdentifier(name)

    companion object
    {
        fun isKeyword(name: String): Boolean = getLexerType(name) in SV_KEYWORDS

        fun isIdentifier(name: String): Boolean = when (getLexerType(name))
        {
            IDENTIFIER, HIERARCHICAL_IDENTIFIER, IDENTIFIER_RULE -> true
            else -> false
        }

        private fun getLexerType(text: String): IElementType?
        {
            val lexer = SvLexer()
            lexer.start(text)
            return if (lexer.tokenEnd == text.length) lexer.tokenType else null
        }
    }
}
