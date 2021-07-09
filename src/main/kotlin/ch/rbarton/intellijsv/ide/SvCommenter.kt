package ch.rbarton.intellijsv.ide

import ch.rbarton.intellijsv.core.psi.SvTypes
import com.intellij.lang.CodeDocumentationAwareCommenter
import com.intellij.psi.PsiComment
import com.intellij.psi.tree.IElementType

class SvCommenter : CodeDocumentationAwareCommenter
{
    override fun getLineCommentPrefix() = "//"

    override fun getBlockCommentPrefix() = "/*"

    override fun getBlockCommentSuffix() = "*/"

    override fun getCommentedBlockCommentPrefix() = "*//*"

    override fun getCommentedBlockCommentSuffix() = "*//*"

    override fun getLineCommentTokenType(): IElementType? = SvTypes.LINE_COMMENT

    override fun getBlockCommentTokenType(): IElementType? = SvTypes.BLOCK_COMMENT

    override fun getDocumentationCommentTokenType(): IElementType? = SvTypes.DOC_COMMENT

    override fun getDocumentationCommentPrefix(): String = "/**"

    override fun getDocumentationCommentLinePrefix(): String = "*"

    override fun getDocumentationCommentSuffix(): String = "*/"

    override fun isDocumentationComment(element: PsiComment): Boolean = element.firstChild == SvTypes.DOC_COMMENT
}