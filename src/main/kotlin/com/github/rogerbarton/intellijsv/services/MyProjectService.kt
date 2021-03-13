package com.github.rogerbarton.intellijsv.services

import com.github.rogerbarton.intellijsv.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
