package ch.rbarton.intellijsv.ide

import ch.rbarton.intellijsv.core.psi.SvUtil
import com.intellij.navigation.ChooseByNameContributor
import com.intellij.navigation.NavigationItem
import com.intellij.openapi.project.Project

/**
 * Fills search results for Navigate to Symbol, Search Anywhere (Shift-Shift)
 */
class SvChooseByNameContributor : ChooseByNameContributor
{
    override fun getNames(project: Project, includeNonProjectItems: Boolean): Array<String>
    {
        // TODO: Also add ModuleInstance, Typedefs
        val names: MutableList<String> = mutableListOf()
        val modules = SvUtil.findModuleIdentifiers(project)
        names.addAll(modules.map { it.identifier.text })
        modules.forEach {
            names.addAll(SvUtil.findPortParameterIdentifiers(it, null).filter { it.identifier != null }.map { it.identifier!!.text })
            names.addAll(SvUtil.findPortNetIdentifiers(it, null).filter { it.identifier != null }.map { it.identifier!!.text })
            names.addAll(SvUtil.findInnerParameterIdentifiers(it, null).filter { it.identifier != null }.map { it.identifier!!.text })
            names.addAll(SvUtil.findInnerNetIdentifiers(it, null).map { it.second.text })
        }
        return names.toTypedArray()
    }

    override fun getItemsByName(
        name: String?, pattern: String?, project: Project, includeNonProjectItems: Boolean
    ): Array<NavigationItem>
    {
        val navItems: MutableList<NavigationItem> = mutableListOf()
        val modules = SvUtil.findModuleIdentifiers(project)
        navItems.addAll(modules.filter { it.identifier.text.equals(pattern) })
        modules.forEach { module ->
            navItems.addAll(SvUtil.findPortParameterIdentifiers(module, pattern).filter { it.identifier != null })
            navItems.addAll(SvUtil.findPortNetIdentifiers(module, pattern).filter { it.identifier != null })
            navItems.addAll(SvUtil.findInnerParameterIdentifiers(module, pattern).filter { it.identifier != null })
            navItems.addAll(SvUtil.findInnerNetIdentifiers(module, pattern).map { it.second })
        }
        return navItems.toTypedArray()
    }

}