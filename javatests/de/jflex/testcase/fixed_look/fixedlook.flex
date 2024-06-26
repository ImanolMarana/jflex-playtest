/*
 * Copyright 2020, Gerwin Klein <lsf@jflex.de>
 * SPDX-License-Identifier: BSD-3-Clause
 */

package de.jflex.testcase.fixed_look;

%%

%public
%class Fixedlook
%integer
%debug

%line
%column

%unicode

%%

"ab"+/"cde"  { System.out.println(yytext()); return 2; }
"abcd"+/"f"  { System.out.println(yytext()); return 7; }
"a"+ / "aa"  { System.out.println(yytext()); return 1; }
"bb" / "b"+  { System.out.println(yytext()); return 3; }
"ab"/"cde"+  { System.out.println(yytext()); return 4; }

[^]         { }
