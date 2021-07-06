package com.github.rogerbarton.intellijsv.ide

import com.github.rogerbarton.intellijsv.core.psi.SvTypes
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext
import org.jetbrains.annotations.NotNull


class SvCompletionContributor : CompletionContributor()
{
    init
    {
        extend(
            CompletionType.BASIC, PlatformPatterns.psiElement(SvTypes.IDENTIFIER),
            object : CompletionProvider<CompletionParameters?>()
            {
                override fun addCompletions(
                    parameters: @NotNull CompletionParameters,
                    context: @NotNull ProcessingContext,
                    resultSet: @NotNull CompletionResultSet
                )
                = resultSet.addElement(LookupElementBuilder.create("Hello"))
            }
        )
    }
}