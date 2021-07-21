package ch.rbarton.intellijsv.ide

import ch.rbarton.intellijsv.core.psi.SvUtil
import com.intellij.navigation.ChooseByNameContributor
import com.intellij.navigation.NavigationItem
import com.intellij.openapi.project.Project
import com.intellij.util.containers.toArray

class SvChooseByNameContributor : ChooseByNameContributor
{
    override fun getNames(project: Project, includeNonProjectItems: Boolean): Array<String> =
        SvUtil.findModuleIdentifiers(project).map { it.identifier.text }.toTypedArray()

    override fun getItemsByName(
        name: String?, pattern: String?, project: Project, includeNonProjectItems: Boolean
    ): Array<NavigationItem> = SvUtil.findModuleIdentifiers(project, name).toTypedArray()

}