// This is a generated file. Not intended for manual editing.
package com.github.rogerbarton.intellijsv.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.rogerbarton.intellijsv.psi.impl.*;

public interface SvTypes {

  IElementType PROPERTY = new SvElementType("PROPERTY");

  IElementType COMMENT = new SvTokenType("COMMENT");
  IElementType CRLF = new SvTokenType("CRLF");
  IElementType KEY = new SvTokenType("KEY");
  IElementType SEPARATOR = new SvTokenType("SEPARATOR");
  IElementType VALUE = new SvTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == PROPERTY) {
        return new SvPropertyImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
