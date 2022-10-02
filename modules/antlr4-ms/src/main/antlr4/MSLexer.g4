
lexer grammar MSLexer;

tokens { INDENT, DEDENT }

@lexer::members {

  /**
   * A queue where extra tokens are pushed on (see the NEWLINE lexer rule for instance).
   * Tokens are returned from the queue first. Once the queue is empty, lexer goes back to input stream for producing tokens.
   */
  private java.util.LinkedList<Token> tokens = new java.util.LinkedList<>();

  // The stack that keeps track of the indentation level.
  private java.util.Stack<Integer> indents = new java.util.Stack<>();

  // The amount of opened braces, brackets and parenthesis.
  private int opened = 0;

  // The most recently produced token.
  private Token lastToken = null;

  /**
   * Add a token to the queue. Tokens are returned from the queue first, before new tokens are extracted from input stream.
   */
  public void schedule(Token t) {
    tokens.offer(t);
  }

  @Override
  public Token nextToken() {

      if (tokens.isEmpty()) {
          Token next = super.nextToken();

          if (next.getType() == EOF) {
              processEOF_NextToken();
              next = tokens.poll();
          } else if(next.getType() == NEWLINE) {
              processNEWLINE_NextToken();
              next = tokens.poll();
          }

          if (lastToken != null && lastToken.getType() == MINUS) {
              switch(next.getType()) {
                  case MINUS:
                    next = commonToken(MSParser.NEWLINE, "\n");
                    createAndScheduleIndent(this._tokenStartCharPositionInLine);
                    schedule(commonToken(MSParser.MINUS, "-"));
                  break;
                  case STRING:
                    int indent = this._tokenStartCharPositionInLine;
                    Token afterNext = super.nextToken();
                    if(afterNext.getType() == COLON) {
                        createAndScheduleIndent(indent); 					//2 - after next (INDENT)
                        schedule(next);										//3 - last (string)
                        next = commonToken(MSParser.NEWLINE, "\n");  //1 - next (NEWLINE)
                    }

                    if (afterNext.getType() == EOF) {
                        processEOF_NextToken();
                    } else if(afterNext.getType() == NEWLINE) {
                        processNEWLINE_NextToken();
                    }
                    else {
                        schedule(afterNext);
                    }
                    break;
              }
          }

          this.lastToken = next;
      } else {
          this.lastToken = tokens.poll();
      }

      return this.lastToken;
  }

  private void processEOF_NextToken() {
      schedule(commonToken(MSParser.NEWLINE, "\n"));

      if (!this.indents.isEmpty()) {

          // Now emit as much DEDENT tokens as needed.
          while (!indents.isEmpty()) {
              this.schedule(createDedent());
              indents.pop();
          }
      }

      // Put the EOF back on the token stream.
      this.schedule(commonToken(MSParser.EOF, "<EOF>"));
  }

  private void processNEWLINE_NextToken() {
      //String newLine = getText().replaceAll("[^\r\n]+", "");
      String spaces = getText().replaceAll("[\r\n]+", "");

      schedule(commonToken(NEWLINE, "\n"));

      int indent = getIndentationCount(spaces);
      int previous = indents.isEmpty() ? 0 : indents.peek();

      if (indent == previous) {
          // skip indents of the same size as the present indent-size
          //skip();
      }
      else if (indent > previous) {
          indents.push(indent);
          schedule(commonToken(MSParser.INDENT, spaces));
      }
      else {
          // Possibly emit more than 1 DEDENT token.
          while(!indents.isEmpty() && indents.peek() > indent) {
              this.schedule(createDedent());
              indents.pop();
          }
      }
  }

  private Token createDedent() {
    CommonToken dedent = commonToken(MSParser.DEDENT, "");
    dedent.setLine(this.lastToken.getLine());
    return dedent;
  }

  private CommonToken commonToken(int type, String text) {
    int stop = this.getCharIndex() - 1;
    int start = text.isEmpty() ? stop : stop - text.length() + 1;
    return new CommonToken(this._tokenFactorySourcePair, type, DEFAULT_TOKEN_CHANNEL, start, stop);
  }

  /**
   * Create a Indent token if given indentation level is greater and schedule it in 'tokens' queue.
   * @param indent indentation level
   */
  private void createAndScheduleIndent(int indent) {
      int previous = indents.isEmpty() ? 0 : indents.peek();
      if (indent > previous) {
          indents.push(indent);
          schedule(commonToken(MSParser.INDENT, "-"));
      }
  }

  // Calculates the indentation of the provided spaces, taking the
  // following rules into account:
  //
  // "Tabs are replaced (from left to right) by one to eight spaces
  //  such that the total number of characters up to and including
  //  the replacement is a multiple of eight [...]"
  //
  //  -- https://docs.python.org/3.1/reference/lexical_analysis.html#indentation
  static int getIndentationCount(String spaces) {

    int count = 0;

    for (char ch : spaces.toCharArray()) {
      switch (ch) {
        case '\t':
          count += 8 - (count % 8);
          break;
        default:
          // A normal space char.
          count++;
      }
    }

    return count;
  }

  boolean atStartOfInput() {
    return super.getCharPositionInLine() == 0 && super.getLine() == 1;
  }

  /**
   * Indentation of a string literal. '-1' means the value is not set.
   */
  private int string_literal_start = -1;

}

NEWLINE
 : ( {atStartOfInput()}?   SPACES
   | ( '\r'? '\n' | '\r' ) SPACES?
   )
   {
     int next = _input.LA(1);
     if (opened > 0 || next == '\r' || next == '\n' || next == '#') {
         // If we're inside a list or on a blank line, ignore all indents,
         // dedents and line breaks.
         skip();
     }
   }
 ;

DECIMAL_INTEGER : NON_ZERO_DIGIT DIGIT* | '0'+ ;
FLOAT_NUMBER : POINT_FLOAT ;

MINUS:              '-';
DOCUMENTSTART:      { super.getCharPositionInLine() == 0 }? '---';
DOCUMENTEND:        { super.getCharPositionInLine() == 0 }? '...';
AMPERSAND :         '&';
STAR :              '*';
OPEN_PAREN :        '(' {opened++;};
CLOSE_PAREN :       ')' {opened--;};
COMMA :             ',';
COLON :             ':';
OPEN_BRACK :        '[' {opened++;};
CLOSE_BRACK :       ']' {opened--;};
OPEN_BRACE :        '{' {opened++;};
CLOSE_BRACE :       '}' {opened--;};
LITERAL_STR_IND:    '|' {string_literal_start=-1;} -> pushMode(LITERAL_STRING);
FOLD_STR_IND:       '>' {string_literal_start=-1;} -> pushMode(LITERAL_STRING);
DOUBLE_QUOTE:       '"' -> pushMode(DOUBLE_QUOTE_STR);

ANCHOR : AMPERSAND NAME ;
ALIAS : STAR NAME ;
fragment NAME : [A-Za-z0-9]+ ;

STRING
 : STRING_START (~(' '|'\r'|'\n'|'"'|':')
 | (':' ~[ \r\n])
 | (' '+ ~[ :#\r\n]) )*
 ;

fragment STRING_START
 : ~('-'|' '|'\r'|'\n'|'"'|':'|'#'|'['|'{'|'&'|'*'|'|')
 | (':' ~[ \r\n])
 | ('-' ~[ \-\r\n])
 | {_input.LA(3) != 45}? '--'
 | {super.getCharPositionInLine() != 0}? '---'
 ;

SKIP1 : ( SPACES | COMMENT | LINE_JOINING ) -> skip ;
UNKNOWN_CHAR : . ;

fragment NON_ZERO_DIGIT : [1-9] ;
fragment DIGIT : [0-9] ;
fragment POINT_FLOAT : INT_PART? FRACTION | INT_PART '.' ;
fragment INT_PART : DIGIT+ ;
fragment FRACTION : '.' DIGIT+ ;
fragment SPACES : [ \t]+ ;
fragment COMMENT : '#' ~[\r\n]* ;
fragment LINE_JOINING : '\\' SPACES? ( '\r'? '\n' | '\r' ) ;

// MODE CHANGE
mode LITERAL_STRING;

NEWLINE_STR_LITERAL: ( '\r'? '\n' | '\r' ) [ \t]*
   {
     int next = _input.LA(1);
     if(!(opened > 0 || next == '\r' || next == '\n' || next == '#')) {
       int indent = indents.isEmpty() ? 0 : indents.peek();
       int space_count = getIndentationCount(getText().replaceAll("[\r\n]+", ""));
       if(space_count <= indent) {
         popMode();
         setType(NEWLINE);
       }
       else if (string_literal_start == -1) {
         string_literal_start = space_count;
       }
     }
   }
 ;

STRING_3
 : ~('\r'|'\n')*
   {
     int extraSpace = _tokenStartCharPositionInLine - string_literal_start;
     if(extraSpace > 0) {
       StringBuilder builder = new StringBuilder();
       for (int i = 0; i < extraSpace; i++) {
         builder.append(' ');
       }
       builder.append(getText());
       setText(builder.toString());
     }
   }
   -> type(STRING)
 ;


// MODE CHANGE
mode DOUBLE_QUOTE_STR;

STRING_4 : (~["\r\n\\] | '\\\\')* -> type(STRING) ;
NEWLINE_STR_QUOTE: ( '\r'? '\n' | '\r' ) [ \t]* ;
DOUBLE_QUOTE2: '"' -> type(DOUBLE_QUOTE), popMode;