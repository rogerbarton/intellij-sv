package ch.rbarton.intellijsv.ide

import ch.rbarton.intellijsv.core.psi.SvPsiUtil
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
        val modules = SvPsiUtil.findModuleDeclarations(project)
        names.addAll(modules.map { it.identifier.text })
        modules.forEach {
            names.addAll(SvPsiUtil.findPortParameters(it, null).filter { it.identifier != null }.map { it.identifier!!.text })
            names.addAll(SvPsiUtil.findPortNets(it, null).filter { it.identifier != null }.map { it.identifier!!.text })
            names.addAll(SvPsiUtil.findInnerParameters(it, null).filter { it.identifier != null }.map { it.identifier!!.text })
            names.addAll(SvPsiUtil.findInnerNets(it, null).map { it.second.text })
        }
        return names.toTypedArray()
    }

    override fun getItemsByName(
        name: String?, pattern: String?, project: Project, includeNonProjectItems: Boolean
    ): Array<NavigationItem>
    {
        val navItems: MutableList<NavigationItem> = mutableListOf()
        val modules = SvPsiUtil.findModuleDeclarations(project)
        navItems.addAll(modules.filter { it.identifier.text.equals(pattern) })
        modules.forEach { module ->
            navItems.addAll(SvPsiUtil.findPortParameters(module, pattern).filter { it.identifier != null })
            navItems.addAll(SvPsiUtil.findPortNets(module, pattern).filter { it.identifier != null })
            navItems.addAll(SvPsiUtil.findInnerParameters(module, pattern).filter { it.identifier != null })
            navItems.addAll(SvPsiUtil.findInnerNets(module, pattern).map { it.second })
        }
        return navItems.toTypedArray()
    }

}