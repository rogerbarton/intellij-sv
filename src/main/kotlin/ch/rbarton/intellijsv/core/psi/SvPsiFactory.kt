package ch.rbarton.intellijsv.core.psi

import ch.rbarton.intellijsv.SvFileType
import com.intellij.openapi.project.Project
import com.intellij.psi.*
import com.intellij.psi.util.PsiTreeUtil

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
        createFromText("module $name;endmodule") ?: error("Failed to create SvModuleDeclaration")

    fun createPortDeclaration(name: String): SvPortDeclaration =
        createFromText("module a(input logic $name);endmodule") ?: error("Failed to create SvPortDeclaration")

    fun createNetLValue(name: String): SvContinuousAssignItem =
        createFromText("module a;assign $name = 0;endmodule") ?: error("Failed to create SvContinuousAssignItem")

    fun createIdentifier(name: String): PsiElement = createModuleDeclaration(name).identifier

    fun createHierarchicalIdentifier(name: String): PsiElement =
        createNetLValue(name).netLvalue.hierarchicalIdentifier
}