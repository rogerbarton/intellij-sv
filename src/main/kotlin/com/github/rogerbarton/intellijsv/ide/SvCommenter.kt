package com.github.rogerbarton.intellijsv.ide

import com.intellij.lang.Commenter

class SvCommenter : Commenter
{
    override fun getLineCommentPrefix() = "// "

    override fun getBlockCommentPrefix() = "/* "

    override fun getBlockCommentSuffix() = " */"

    override fun getCommentedBlockCommentPrefix() = " *//* "

    override fun getCommentedBlockCommentSuffix() = " *//* "
}