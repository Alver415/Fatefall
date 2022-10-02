
parser grammar MSParser;

options {
    tokenVocab = MSLexer;
}

file
 : NEWLINE*
 (
   (DOCUMENTSTART NEWLINE?)? document (DOCUMENTEND NEWLINE)? (NEWLINE* (DOCUMENTSTART NEWLINE) document (DOCUMENTEND NEWLINE?)?)*
 )
 NEWLINE* EOF
 ;

document
 : object | value
 ;

object
 : (NEWLINE* mapping NEWLINE?)+
 ;

key
 : STRING
 ;

value
 : value_scalar | NEWLINE INDENT value_scalar NEWLINE DEDENT | multiline_string | string_literal | string_folded | string_double_quoted
 ;

value_scalar
 : number | STRING
 ;

multiline_string
 : value_scalar? NEWLINE INDENT ( value_scalar (NEWLINE value_scalar)* | multiline_string)+ NEWLINE? DEDENT
 ;

string_literal
 : LITERAL_STR_IND (NEWLINE_STR_LITERAL+ STRING)*
 ;

string_folded
 : FOLD_STR_IND (NEWLINE_STR_LITERAL+ STRING)*
 ;

string_double_quoted
 : DOUBLE_QUOTE STRING (NEWLINE_STR_QUOTE+ STRING)* DOUBLE_QUOTE
 ;

mapping
 : key COLON NEWLINE+ INDENT object DEDENT
 | key COLON value?
 ;

number
 : number_integer
 | number_float
 ;

number_integer
 : DECIMAL_INTEGER
 ;

number_float
 : FLOAT_NUMBER
 ;