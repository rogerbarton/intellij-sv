package ch.rbarton.intellijsv.core.psi

import ch.rbarton.intellijsv.SvFileType
import com.intellij.openapi.project.Project
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.descendantsOfType

class SvPsiFactory(private val project: Project)
{
    fun createFile(text: CharSequence): SvFile =
        PsiFileFactory.getInstance(project)
            .createFileFromText("dummy.sv", SvFileType.INSTANCE, text) as SvFile

    private inline fun <reified T : PsiElement> createFromText(code: CharSequence): T?
    {
        val file = createFile(code)
        return PsiTreeUtil.findChildOfType(file, T::class.java, true)
    }

    fun createModuleDeclaration(name: String): SvModuleDeclaration =
        createFromText("module ${name};endmodule") ?: error("Failed to create SvModuleDeclaration")

    fun createIdentifier(name: String): PsiElement = createModuleDeclaration(name).identifier
}