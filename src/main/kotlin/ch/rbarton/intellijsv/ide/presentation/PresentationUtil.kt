package ch.rbarton.intellijsv.ide.presentation

import ch.rbarton.intellijsv.core.psi.*
import com.intellij.ide.projectView.PresentationData
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement

/**
 * Defines how to display the element in the StructureView.
 * This is generally simpler than getPresentation with the location omitted.
 */
fun getPresentationStructureView(psi: PsiElement): ItemPresentation
{
    val name: String? = when (psi)
    {
        is SvModuleDeclaration -> psi.name
        is SvParameterDeclaration -> psi.name
        is SvPortDeclaration -> psi.name
        is SvModuleItem -> when
        {
            psi.moduleInstantiation != null -> "${psi.moduleInstantiation!!.moduleIdentifier.text} ${psi.moduleInstantiation!!.instanceIdentifier?.text}"
            psi.typeDeclaration != null -> psi.typeDeclaration!!.identifier.text
            psi.parameterDeclaration != null -> psi.parameterDeclaration!!.name
            psi.netDeclaration != null ->
            {
                buildString {
                    val decl = psi.netDeclaration!!
                    append(decl.type.text)
                    append(" ")
                    append(decl.netDeclarationAssignmentList.joinToString(", ") { it.identifier.text })
                }
            }
            psi.alwaysConstruct != null ->
            {
                val always = psi.alwaysConstruct!!
                if (always.alwaysKeyword.alwaysFf != null && always.statement is SvTimingControlStmt)
                    buildString {
                        append(always.alwaysKeyword.text)
                        val timing = always.statement as SvTimingControlStmt
                        when
                        {
                            timing.eventControl != null -> append(timing.eventControl!!.text)
                            timing.delayControl != null -> append(timing.delayControl!!.text)
                            timing.delayCycle != null -> append(timing.delayCycle!!.text)
                        }
                    }
                else
                    psi.alwaysConstruct!!.alwaysKeyword.text
            }
            else -> null
        }
        else -> null
    }

    return PresentationData(name, null, psi.getIcon(0), null)
}