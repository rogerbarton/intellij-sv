package ch.rbarton.intellijsv.core.parser;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static ch.rbarton.intellijsv.core.psi.SvTypes.*;

%%

%{
  public _SvLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _SvLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

TIME_LITERAL=[0-9][_\d]*(s|ms|us|ns|ps|fs)
BINARY_NUMBER=[0-9][_\d]*'[sS]?[bB][01XxZz?][_01XxZz?]*
OCTAL_NUMBER=[0-9][_\d]*'[sS]?[oO][0-7XxZz?][_0-7XxZz?]*
HEX_NUMBER=[0-9][_\d]*'[sS]?[hH][0-9ABCDEFXxZz?][_0-9ABCDEFXxZz?]*
UNSIGNED_NUMBER=[0-9][_\d]*
SPECIAL_NUMBER='0|'1|'|x|X|z|Z
WHITE_SPACE=[\s\r\n]+
IDENTIFIER=[a-zA-Z_][a-zA-Z0-9_$]*
LINE_COMMENT="//".*
DOC_COMMENT="/"\*\*(.|\n)*?\*"/"
BLOCK_COMMENT="/"\*(.|\n)*?\*"/"
STRING_LITERAL=\"[^\"]*?\"

%%
<YYINITIAL> {
  {WHITE_SPACE}          { return WHITE_SPACE; }

  "{"                    { return LBRACE; }
  "}"                    { return RBRACE; }
  "["                    { return LBRACK; }
  "]"                    { return RBRACK; }
  "(*"                   { return LPARENSTAR; }
  "("                    { return LPAREN; }
  "*)"                   { return RPARENSTAR; }
  ")"                    { return RPAREN; }
  "::"                   { return COLONCOLON; }
  ":"                    { return COLON; }
  ";"                    { return SEMICOLON; }
  ","                    { return COMMA; }
  "#"                    { return SHA; }
  "##"                   { return SHASHA; }
  "."                    { return DOT; }
  "_"                    { return UNDERSCORE; }
  "==?"                  { return EQEQQS; }
  "==="                  { return EQEQEQ; }
  "=="                   { return EQEQ; }
  "="                    { return EQ; }
  "!=?"                  { return EXCLEQQS; }
  "!=="                  { return EXCLEQEQ; }
  "!="                   { return EXCLEQ; }
  "!"                    { return EXCL; }
  "+="                   { return PLUSEQ; }
  "++"                   { return PLUSPLUS; }
  "+"                    { return PLUS; }
  "-="                   { return MINUSEQ; }
  "->"                   { return MINUSGT; }
  "--"                   { return MINUSMINUS; }
  "-"                    { return MINUS; }
  "**"                   { return MULMUL; }
  "*="                   { return MULEQ; }
  "*"                    { return MUL; }
  "/="                   { return DIVEQ; }
  "/"                    { return DIV; }
  "%="                   { return PCTEQ; }
  "%"                    { return PCT; }
  "~&"                   { return TILDAND; }
  "~|"                   { return TILDOR; }
  "~^"                   { return TILDHAT; }
  "~"                    { return TILD; }
  "^~"                   { return HATTILD; }
  "^="                   { return HATEQ; }
  "^"                    { return HAT; }
  ">="                   { return GTEQ; }
  ">>>="                 { return GTGTGTEQ; }
  ">>="                  { return GTGTEQ; }
  ">>>"                  { return GTGTGT; }
  ">>"                   { return GTGT; }
  ">"                    { return GT; }
  "<="                   { return LTEQ; }
  "<->"                  { return LTMINUSLT; }
  "<<<="                 { return LTLTLTEQ; }
  "<<="                  { return LTLTEQ; }
  "<<<"                  { return LTLTLT; }
  "<<"                   { return LTLT; }
  "<"                    { return LT; }
  "|="                   { return OREQ; }
  "||"                   { return OROR; }
  "|"                    { return OR_; }
  "&="                   { return ANDEQ; }
  "&&&"                  { return ANDANDAND; }
  "&&"                   { return ANDAND; }
  "&"                    { return AND_; }
  "timeunit"             { return TIMEUNIT; }
  "timeprecision"        { return TIMEPRECISION; }
  "module"               { return MODULE; }
  "endmodule"            { return ENDMODULE; }
  "parameter"            { return PARAMETER; }
  "localparam"           { return LOCALPARAM; }
  "input"                { return INPUT; }
  "output"               { return OUTPUT; }
  "inout"                { return INOUT; }
  "ref"                  { return REF; }
  "assign"               { return ASSIGN; }
  "initial"              { return INITIAL; }
  "final"                { return FINAL; }
  "always_comb"          { return ALWAYS_COMB; }
  "always_ff"            { return ALWAYS_FF; }
  "always_latch"         { return ALWAYS_LATCH; }
  "always"               { return ALWAYS; }
  "begin"                { return BEGIN; }
  "end"                  { return END; }
  "if"                   { return IF; }
  "else"                 { return ELSE; }
  "unique"               { return UNIQUE; }
  "unique0"              { return UNIQUE0; }
  "priority"             { return PRIORITY; }
  "iff"                  { return IFF; }
  "or"                   { return OR; }
  "posedge"              { return POSEDGE; }
  "negedge"              { return NEGEDGE; }
  "edge"                 { return EDGE; }
  "generate"             { return GENERATE; }
  "endgenerate"          { return ENDGENERATE; }
  "TODO"                 { return TODO; }
  "string"               { return STRING; }
  "typedef"              { return TYPEDEF; }
  "enum"                 { return ENUM; }
  "struct"               { return STRUCT; }
  "union"                { return UNION; }
  "class"                { return CLASS; }
  "packed"               { return PACKED; }
  "signed"               { return SIGNED; }
  "unsigned"             { return UNSIGNED; }
  "logic"                { return LOGIC; }
  "int"                  { return INT; }
  "wire"                 { return WIRE; }
  "reg"                  { return REG; }
  "byte"                 { return BYTE; }
  "shortint"             { return SHORTINT; }
  "longint"              { return LONGINT; }
  "integer"              { return INTEGER; }
  "time"                 { return TIME; }
  "bit"                  { return BIT; }
  "shortreal"            { return SHORTREAL; }
  "real"                 { return REAL; }
  "realtime"             { return REALTIME; }
  "const"                { return CONST; }

  {TIME_LITERAL}         { return TIME_LITERAL; }
  {BINARY_NUMBER}        { return BINARY_NUMBER; }
  {OCTAL_NUMBER}         { return OCTAL_NUMBER; }
  {HEX_NUMBER}           { return HEX_NUMBER; }
  {UNSIGNED_NUMBER}      { return UNSIGNED_NUMBER; }
  {SPECIAL_NUMBER}       { return SPECIAL_NUMBER; }
  {WHITE_SPACE}          { return WHITE_SPACE; }
  {IDENTIFIER}           { return IDENTIFIER; }
  {LINE_COMMENT}         { return LINE_COMMENT; }
  {DOC_COMMENT}          { return DOC_COMMENT; }
  {BLOCK_COMMENT}        { return BLOCK_COMMENT; }
  {STRING_LITERAL}       { return STRING_LITERAL; }

}

[^] { return BAD_CHARACTER; }
