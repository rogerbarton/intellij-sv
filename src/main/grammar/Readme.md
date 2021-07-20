# Grammar

The `.bnf` files define the grammar using Backus-Naur Form (BNF)
with [Grammar-Kit](https://github.com/JetBrains/Grammar-Kit)

Usage:

- Use right-click context menu for various actions, e.g. to generate the parser/lexer
- Generated classes are in `src/main/gen`
- Use the Live Preview, with 'Start Grammar Highlighting', and PsiViewer plugin to inspect a sample

Writing bnf:

- Each *rule* corresponds to a generated PSI class
- The parser is made robust to incorrect/incomplete code changes with the `pin` and `recoverWhile` attributes
- Additional functionality is added with interfaces and mixin classes, attributes `implements` and `mixin`
- The `methods` attribute is used to rename specific getters or add specialized functionality that resides in
  the `SvPsiImplUtil`
- Note that an attribute may be specified globally at the top, potentially via a regex, or for each rule
- Every symbol that is used needs to be defined as a token

Naming:

- Normal rules are `CamelCase`
- Variations of rules are `CamelCase_with_underscores`, e.g. for a recover rule
- Meta (macro) rules are `CAPS_CASE`
- Tokens are `CAPS_CASE`
- Keywords are unquoted, symbols and are single-quoted
- Certain rules may be items of a common rule, e.g. `Expression`. These have a shared suffix, e.g. `Expr`
    - These generally use `extends` to keep the PSI tree shallow

Formatting:

- Separate large case distinctions over several lines