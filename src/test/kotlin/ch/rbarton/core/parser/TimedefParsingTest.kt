package ch.rbarton.core.parser

import ch.rbarton.intellijsv.core.psi.SvParserDefinition
import com.intellij.testFramework.ParsingTestCase;

class TimedefParsingTest : ParsingTestCase("src/test/resources", "sv", SvParserDefinition())
{
    override fun skipSpaces() = true

    override fun includeRanges() = true
}