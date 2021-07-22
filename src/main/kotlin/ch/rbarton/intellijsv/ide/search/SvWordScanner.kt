package ch.rbarton.intellijsv.ide.search

import ch.rbarton.intellijsv.core.lexer.SvLexer
import ch.rbarton.intellijsv.core.psi.SV_COMMENTS
import ch.rbarton.intellijsv.core.psi.SV_STRING_LITERALS
import ch.rbarton.intellijsv.core.psi.SvTypes.IDENTIFIER
import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.psi.tree.TokenSet

class SvWordScanner : DefaultWordsScanner(
    SvLexer(),
    TokenSet.create(IDENTIFIER),
    SV_COMMENTS,
    SV_STRING_LITERALS
)
{

}
