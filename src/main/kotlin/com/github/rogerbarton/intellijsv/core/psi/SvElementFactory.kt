package com.github.rogerbarton.intellijsv.core.psi

import com.github.rogerbarton.intellijsv.SvFileType
import com.intellij.openapi.project.Project
import com.intellij.psi.*;

class SvElementFactory
{
    companion object {
        fun createFile(project: Project, text: String): SvFile
        {
            val name: String = "dummy.sv"
            return PsiFileFactory.getInstance(project).createFileFromText(name, SvFileType.INSTANCE, text) as SvFile
        }

        fun createModuleDeclaration(project: Project, name: String): SvModuleDeclaration
        {
            val file: SvFile = createFile(project, name)
            return file.firstChild as SvModuleDeclaration
        }
    }
}