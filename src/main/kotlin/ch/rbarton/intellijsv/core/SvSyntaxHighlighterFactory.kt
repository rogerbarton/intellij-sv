package ch.rbarton.intellijsv.core

import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.project.Project

class SvSyntaxHighlighterFactory : SyntaxHighlighterFactory()
{
    override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): SyntaxHighlighter =
        SvSyntaxHighlighter()
}