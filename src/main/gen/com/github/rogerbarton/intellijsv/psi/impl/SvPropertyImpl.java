// This is a generated file. Not intended for manual editing.
package com.github.rogerbarton.intellijsv.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.rogerbarton.intellijsv.psi.SvTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.rogerbarton.intellijsv.psi.*;

public class SvPropertyImpl extends ASTWrapperPsiElement implements SvProperty {

  public SvPropertyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SvVisitor visitor) {
    visitor.visitProperty(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SvVisitor) accept((SvVisitor)visitor);
    else super.accept(visitor);
  }

}
