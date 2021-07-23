package ch.rbarton.intellijsv.core.psi.mixin

import ch.rbarton.intellijsv.core.psi.SvModuleDeclaration
import ch.rbarton.intellijsv.core.psi.SvNetDeclarationAssignment
import ch.rbarton.intellijsv.core.psi.ancestorStrict
import ch.rbarton.intellijsv.core.psi.ext.SvNamedIdentifierOwnerImpl
import ch.rbarton.intellijsv.ide.SvIcons
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.search.LocalSearchScope
import com.intellij.psi.search.SearchScope
import javax.swing.Icon

abstract class SvNetDeclarationAssignmentMixin(node: ASTNode) : SvNetDeclarationAssignment, SvNamedIdentifierOwnerImpl(node)
{
    override fun getIcon(flags: Int): Icon = SvIcons.SV_NET

    // -- SvNameIdentifierOwner
    override fun getNameIdentifier(): PsiElement? = identifier

    // Make finding usages faster
    override fun getUseScope(): SearchScope = LocalSearchScope(ancestorStrict<SvModuleDeclaration>() ?: this)
}