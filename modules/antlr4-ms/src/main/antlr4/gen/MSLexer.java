// Generated from C:/Users/Alver/Development/projects/Fatefall/modules/antlr4-ms/src/main/antlr4\MSLexer.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MSLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INDENT=1, DEDENT=2, NEWLINE=3, DECIMAL_INTEGER=4, FLOAT_NUMBER=5, MINUS=6, 
		DOCUMENTSTART=7, DOCUMENTEND=8, AMPERSAND=9, STAR=10, OPEN_PAREN=11, CLOSE_PAREN=12, 
		COMMA=13, COLON=14, OPEN_BRACK=15, CLOSE_BRACK=16, OPEN_BRACE=17, CLOSE_BRACE=18, 
		LITERAL_STR_IND=19, FOLD_STR_IND=20, DOUBLE_QUOTE=21, ANCHOR=22, ALIAS=23, 
		STRING=24, SKIP1=25, UNKNOWN_CHAR=26, NEWLINE_STR_LITERAL=27, NEWLINE_STR_QUOTE=28;
	public static final int
		LITERAL_STRING=1, DOUBLE_QUOTE_STR=2;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "LITERAL_STRING", "DOUBLE_QUOTE_STR"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"NEWLINE", "DECIMAL_INTEGER", "FLOAT_NUMBER", "MINUS", "DOCUMENTSTART", 
			"DOCUMENTEND", "AMPERSAND", "STAR", "OPEN_PAREN", "CLOSE_PAREN", "COMMA", 
			"COLON", "OPEN_BRACK", "CLOSE_BRACK", "OPEN_BRACE", "CLOSE_BRACE", "LITERAL_STR_IND", 
			"FOLD_STR_IND", "DOUBLE_QUOTE", "ANCHOR", "ALIAS", "NAME", "STRING", 
			"STRING_START", "SKIP1", "UNKNOWN_CHAR", "NON_ZERO_DIGIT", "DIGIT", "POINT_FLOAT", 
			"INT_PART", "FRACTION", "SPACES", "COMMENT", "LINE_JOINING", "NEWLINE_STR_LITERAL", 
			"STRING_3", "STRING_4", "NEWLINE_STR_QUOTE", "DOUBLE_QUOTE2"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, "'-'", null, null, "'&'", "'*'", 
			"'('", "')'", "','", "':'", "'['", "']'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "INDENT", "DEDENT", "NEWLINE", "DECIMAL_INTEGER", "FLOAT_NUMBER", 
			"MINUS", "DOCUMENTSTART", "DOCUMENTEND", "AMPERSAND", "STAR", "OPEN_PAREN", 
			"CLOSE_PAREN", "COMMA", "COLON", "OPEN_BRACK", "CLOSE_BRACK", "OPEN_BRACE", 
			"CLOSE_BRACE", "LITERAL_STR_IND", "FOLD_STR_IND", "DOUBLE_QUOTE", "ANCHOR", 
			"ALIAS", "STRING", "SKIP1", "UNKNOWN_CHAR", "NEWLINE_STR_LITERAL", "NEWLINE_STR_QUOTE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}



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



	public MSLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MSLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 0:
			NEWLINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 8:
			OPEN_PAREN_action((RuleContext)_localctx, actionIndex);
			break;
		case 9:
			CLOSE_PAREN_action((RuleContext)_localctx, actionIndex);
			break;
		case 12:
			OPEN_BRACK_action((RuleContext)_localctx, actionIndex);
			break;
		case 13:
			CLOSE_BRACK_action((RuleContext)_localctx, actionIndex);
			break;
		case 14:
			OPEN_BRACE_action((RuleContext)_localctx, actionIndex);
			break;
		case 15:
			CLOSE_BRACE_action((RuleContext)_localctx, actionIndex);
			break;
		case 16:
			LITERAL_STR_IND_action((RuleContext)_localctx, actionIndex);
			break;
		case 17:
			FOLD_STR_IND_action((RuleContext)_localctx, actionIndex);
			break;
		case 34:
			NEWLINE_STR_LITERAL_action((RuleContext)_localctx, actionIndex);
			break;
		case 35:
			STRING_3_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void NEWLINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:

			     int next = _input.LA(1);
			     if (opened > 0 || next == '\r' || next == '\n' || next == '#') {
			         // If we're inside a list or on a blank line, ignore all indents,
			         // dedents and line breaks.
			         skip();
			     }
			   
			break;
		}
	}
	private void OPEN_PAREN_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			opened++;
			break;
		}
	}
	private void CLOSE_PAREN_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:
			opened--;
			break;
		}
	}
	private void OPEN_BRACK_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:
			opened++;
			break;
		}
	}
	private void CLOSE_BRACK_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4:
			opened--;
			break;
		}
	}
	private void OPEN_BRACE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5:
			opened++;
			break;
		}
	}
	private void CLOSE_BRACE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6:
			opened--;
			break;
		}
	}
	private void LITERAL_STR_IND_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 7:
			string_literal_start=-1;
			break;
		}
	}
	private void FOLD_STR_IND_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 8:
			string_literal_start=-1;
			break;
		}
	}
	private void NEWLINE_STR_LITERAL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 9:

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
			   
			break;
		}
	}
	private void STRING_3_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 10:

			     int extraSpace = _tokenStartCharPositionInLine - string_literal_start;
			     if(extraSpace > 0) {
			       StringBuilder builder = new StringBuilder();
			       for (int i = 0; i < extraSpace; i++) {
			         builder.append(' ');
			       }
			       builder.append(getText());
			       setText(builder.toString());
			     }
			   
			break;
		}
	}
	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return NEWLINE_sempred((RuleContext)_localctx, predIndex);
		case 4:
			return DOCUMENTSTART_sempred((RuleContext)_localctx, predIndex);
		case 5:
			return DOCUMENTEND_sempred((RuleContext)_localctx, predIndex);
		case 23:
			return STRING_START_sempred((RuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean NEWLINE_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return atStartOfInput();
		}
		return true;
	}
	private boolean DOCUMENTSTART_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return  super.getCharPositionInLine() == 0 ;
		}
		return true;
	}
	private boolean DOCUMENTEND_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return  super.getCharPositionInLine() == 0 ;
		}
		return true;
	}
	private boolean STRING_START_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return _input.LA(3) != 45;
		case 4:
			return super.getCharPositionInLine() != 0;
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0000\u001c\u013a\u0006\uffff\uffff\u0006\uffff\uffff\u0006\uffff"+
		"\uffff\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002\u0002\u0007"+
		"\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002\u0005\u0007"+
		"\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002\b\u0007\b"+
		"\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002\f\u0007"+
		"\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f\u0002"+
		"\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012\u0002"+
		"\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015\u0002"+
		"\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018\u0002"+
		"\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b\u0002"+
		"\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e\u0002"+
		"\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002#"+
		"\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0003\u0000U\b\u0000\u0001\u0000\u0001\u0000\u0003\u0000"+
		"Y\b\u0000\u0001\u0000\u0003\u0000\\\b\u0000\u0003\u0000^\b\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0005\u0001d\b\u0001\n\u0001"+
		"\f\u0001g\t\u0001\u0001\u0001\u0004\u0001j\b\u0001\u000b\u0001\f\u0001"+
		"k\u0003\u0001n\b\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0004\u0015\u00ad"+
		"\b\u0015\u000b\u0015\f\u0015\u00ae\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0004\u0016\u00b6\b\u0016\u000b\u0016\f\u0016"+
		"\u00b7\u0001\u0016\u0005\u0016\u00bb\b\u0016\n\u0016\f\u0016\u00be\t\u0016"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0003\u0017\u00cc\b\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018"+
		"\u00d1\b\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u001a"+
		"\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0003\u001c\u00dc\b\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0003\u001c\u00e2\b\u001c"+
		"\u0001\u001d\u0004\u001d\u00e5\b\u001d\u000b\u001d\f\u001d\u00e6\u0001"+
		"\u001e\u0001\u001e\u0004\u001e\u00eb\b\u001e\u000b\u001e\f\u001e\u00ec"+
		"\u0001\u001f\u0004\u001f\u00f0\b\u001f\u000b\u001f\f\u001f\u00f1\u0001"+
		" \u0001 \u0005 \u00f6\b \n \f \u00f9\t \u0001!\u0001!\u0003!\u00fd\b!"+
		"\u0001!\u0003!\u0100\b!\u0001!\u0001!\u0003!\u0104\b!\u0001\"\u0003\""+
		"\u0107\b\"\u0001\"\u0001\"\u0003\"\u010b\b\"\u0001\"\u0005\"\u010e\b\""+
		"\n\"\f\"\u0111\t\"\u0001\"\u0001\"\u0001#\u0005#\u0116\b#\n#\f#\u0119"+
		"\t#\u0001#\u0001#\u0001#\u0001#\u0001$\u0001$\u0001$\u0005$\u0122\b$\n"+
		"$\f$\u0125\t$\u0001$\u0001$\u0001%\u0003%\u012a\b%\u0001%\u0001%\u0003"+
		"%\u012e\b%\u0001%\u0005%\u0131\b%\n%\f%\u0134\t%\u0001&\u0001&\u0001&"+
		"\u0001&\u0001&\u0000\u0000\'\u0003\u0003\u0005\u0004\u0007\u0005\t\u0006"+
		"\u000b\u0007\r\b\u000f\t\u0011\n\u0013\u000b\u0015\f\u0017\r\u0019\u000e"+
		"\u001b\u000f\u001d\u0010\u001f\u0011!\u0012#\u0013%\u0014\'\u0015)\u0016"+
		"+\u0017-\u0000/\u00181\u00003\u00195\u001a7\u00009\u0000;\u0000=\u0000"+
		"?\u0000A\u0000C\u0000E\u0000G\u001bI\u0000K\u0000M\u001cO\u0000\u0003"+
		"\u0000\u0001\u0002\u000b\u0003\u000009AZaz\u0005\u0000\n\n\r\r  \"\":"+
		":\u0003\u0000\n\n\r\r  \u0005\u0000\n\n\r\r  ##::\n\u0000\n\n\r\r  \""+
		"#&&**--::[[{|\u0004\u0000\n\n\r\r  --\u0001\u000019\u0001\u000009\u0002"+
		"\u0000\t\t  \u0002\u0000\n\n\r\r\u0004\u0000\n\n\r\r\"\"\\\\\u0151\u0000"+
		"\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000"+
		"\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b"+
		"\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001"+
		"\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001"+
		"\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001"+
		"\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001"+
		"\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001"+
		"\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000"+
		"\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000"+
		"\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000/"+
		"\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001\u0000"+
		"\u0000\u0000\u0001G\u0001\u0000\u0000\u0000\u0001I\u0001\u0000\u0000\u0000"+
		"\u0002K\u0001\u0000\u0000\u0000\u0002M\u0001\u0000\u0000\u0000\u0002O"+
		"\u0001\u0000\u0000\u0000\u0003]\u0001\u0000\u0000\u0000\u0005m\u0001\u0000"+
		"\u0000\u0000\u0007o\u0001\u0000\u0000\u0000\tq\u0001\u0000\u0000\u0000"+
		"\u000bs\u0001\u0000\u0000\u0000\rx\u0001\u0000\u0000\u0000\u000f}\u0001"+
		"\u0000\u0000\u0000\u0011\u007f\u0001\u0000\u0000\u0000\u0013\u0081\u0001"+
		"\u0000\u0000\u0000\u0015\u0084\u0001\u0000\u0000\u0000\u0017\u0087\u0001"+
		"\u0000\u0000\u0000\u0019\u0089\u0001\u0000\u0000\u0000\u001b\u008b\u0001"+
		"\u0000\u0000\u0000\u001d\u008e\u0001\u0000\u0000\u0000\u001f\u0091\u0001"+
		"\u0000\u0000\u0000!\u0094\u0001\u0000\u0000\u0000#\u0097\u0001\u0000\u0000"+
		"\u0000%\u009c\u0001\u0000\u0000\u0000\'\u00a1\u0001\u0000\u0000\u0000"+
		")\u00a5\u0001\u0000\u0000\u0000+\u00a8\u0001\u0000\u0000\u0000-\u00ac"+
		"\u0001\u0000\u0000\u0000/\u00b0\u0001\u0000\u0000\u00001\u00cb\u0001\u0000"+
		"\u0000\u00003\u00d0\u0001\u0000\u0000\u00005\u00d4\u0001\u0000\u0000\u0000"+
		"7\u00d6\u0001\u0000\u0000\u00009\u00d8\u0001\u0000\u0000\u0000;\u00e1"+
		"\u0001\u0000\u0000\u0000=\u00e4\u0001\u0000\u0000\u0000?\u00e8\u0001\u0000"+
		"\u0000\u0000A\u00ef\u0001\u0000\u0000\u0000C\u00f3\u0001\u0000\u0000\u0000"+
		"E\u00fa\u0001\u0000\u0000\u0000G\u010a\u0001\u0000\u0000\u0000I\u0117"+
		"\u0001\u0000\u0000\u0000K\u0123\u0001\u0000\u0000\u0000M\u012d\u0001\u0000"+
		"\u0000\u0000O\u0135\u0001\u0000\u0000\u0000QR\u0004\u0000\u0000\u0000"+
		"R^\u0003A\u001f\u0000SU\u0005\r\u0000\u0000TS\u0001\u0000\u0000\u0000"+
		"TU\u0001\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000VY\u0005\n\u0000\u0000"+
		"WY\u0005\r\u0000\u0000XT\u0001\u0000\u0000\u0000XW\u0001\u0000\u0000\u0000"+
		"Y[\u0001\u0000\u0000\u0000Z\\\u0003A\u001f\u0000[Z\u0001\u0000\u0000\u0000"+
		"[\\\u0001\u0000\u0000\u0000\\^\u0001\u0000\u0000\u0000]Q\u0001\u0000\u0000"+
		"\u0000]X\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000\u0000_`\u0006\u0000"+
		"\u0000\u0000`\u0004\u0001\u0000\u0000\u0000ae\u00037\u001a\u0000bd\u0003"+
		"9\u001b\u0000cb\u0001\u0000\u0000\u0000dg\u0001\u0000\u0000\u0000ec\u0001"+
		"\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000fn\u0001\u0000\u0000\u0000"+
		"ge\u0001\u0000\u0000\u0000hj\u00050\u0000\u0000ih\u0001\u0000\u0000\u0000"+
		"jk\u0001\u0000\u0000\u0000ki\u0001\u0000\u0000\u0000kl\u0001\u0000\u0000"+
		"\u0000ln\u0001\u0000\u0000\u0000ma\u0001\u0000\u0000\u0000mi\u0001\u0000"+
		"\u0000\u0000n\u0006\u0001\u0000\u0000\u0000op\u0003;\u001c\u0000p\b\u0001"+
		"\u0000\u0000\u0000qr\u0005-\u0000\u0000r\n\u0001\u0000\u0000\u0000st\u0004"+
		"\u0004\u0001\u0000tu\u0005-\u0000\u0000uv\u0005-\u0000\u0000vw\u0005-"+
		"\u0000\u0000w\f\u0001\u0000\u0000\u0000xy\u0004\u0005\u0002\u0000yz\u0005"+
		".\u0000\u0000z{\u0005.\u0000\u0000{|\u0005.\u0000\u0000|\u000e\u0001\u0000"+
		"\u0000\u0000}~\u0005&\u0000\u0000~\u0010\u0001\u0000\u0000\u0000\u007f"+
		"\u0080\u0005*\u0000\u0000\u0080\u0012\u0001\u0000\u0000\u0000\u0081\u0082"+
		"\u0005(\u0000\u0000\u0082\u0083\u0006\b\u0001\u0000\u0083\u0014\u0001"+
		"\u0000\u0000\u0000\u0084\u0085\u0005)\u0000\u0000\u0085\u0086\u0006\t"+
		"\u0002\u0000\u0086\u0016\u0001\u0000\u0000\u0000\u0087\u0088\u0005,\u0000"+
		"\u0000\u0088\u0018\u0001\u0000\u0000\u0000\u0089\u008a\u0005:\u0000\u0000"+
		"\u008a\u001a\u0001\u0000\u0000\u0000\u008b\u008c\u0005[\u0000\u0000\u008c"+
		"\u008d\u0006\f\u0003\u0000\u008d\u001c\u0001\u0000\u0000\u0000\u008e\u008f"+
		"\u0005]\u0000\u0000\u008f\u0090\u0006\r\u0004\u0000\u0090\u001e\u0001"+
		"\u0000\u0000\u0000\u0091\u0092\u0005{\u0000\u0000\u0092\u0093\u0006\u000e"+
		"\u0005\u0000\u0093 \u0001\u0000\u0000\u0000\u0094\u0095\u0005}\u0000\u0000"+
		"\u0095\u0096\u0006\u000f\u0006\u0000\u0096\"\u0001\u0000\u0000\u0000\u0097"+
		"\u0098\u0005|\u0000\u0000\u0098\u0099\u0006\u0010\u0007\u0000\u0099\u009a"+
		"\u0001\u0000\u0000\u0000\u009a\u009b\u0006\u0010\b\u0000\u009b$\u0001"+
		"\u0000\u0000\u0000\u009c\u009d\u0005>\u0000\u0000\u009d\u009e\u0006\u0011"+
		"\t\u0000\u009e\u009f\u0001\u0000\u0000\u0000\u009f\u00a0\u0006\u0011\b"+
		"\u0000\u00a0&\u0001\u0000\u0000\u0000\u00a1\u00a2\u0005\"\u0000\u0000"+
		"\u00a2\u00a3\u0001\u0000\u0000\u0000\u00a3\u00a4\u0006\u0012\n\u0000\u00a4"+
		"(\u0001\u0000\u0000\u0000\u00a5\u00a6\u0003\u000f\u0006\u0000\u00a6\u00a7"+
		"\u0003-\u0015\u0000\u00a7*\u0001\u0000\u0000\u0000\u00a8\u00a9\u0003\u0011"+
		"\u0007\u0000\u00a9\u00aa\u0003-\u0015\u0000\u00aa,\u0001\u0000\u0000\u0000"+
		"\u00ab\u00ad\u0007\u0000\u0000\u0000\u00ac\u00ab\u0001\u0000\u0000\u0000"+
		"\u00ad\u00ae\u0001\u0000\u0000\u0000\u00ae\u00ac\u0001\u0000\u0000\u0000"+
		"\u00ae\u00af\u0001\u0000\u0000\u0000\u00af.\u0001\u0000\u0000\u0000\u00b0"+
		"\u00bc\u00031\u0017\u0000\u00b1\u00bb\b\u0001\u0000\u0000\u00b2\u00b3"+
		"\u0005:\u0000\u0000\u00b3\u00bb\b\u0002\u0000\u0000\u00b4\u00b6\u0005"+
		" \u0000\u0000\u00b5\u00b4\u0001\u0000\u0000\u0000\u00b6\u00b7\u0001\u0000"+
		"\u0000\u0000\u00b7\u00b5\u0001\u0000\u0000\u0000\u00b7\u00b8\u0001\u0000"+
		"\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9\u00bb\b\u0003\u0000"+
		"\u0000\u00ba\u00b1\u0001\u0000\u0000\u0000\u00ba\u00b2\u0001\u0000\u0000"+
		"\u0000\u00ba\u00b5\u0001\u0000\u0000\u0000\u00bb\u00be\u0001\u0000\u0000"+
		"\u0000\u00bc\u00ba\u0001\u0000\u0000\u0000\u00bc\u00bd\u0001\u0000\u0000"+
		"\u0000\u00bd0\u0001\u0000\u0000\u0000\u00be\u00bc\u0001\u0000\u0000\u0000"+
		"\u00bf\u00cc\b\u0004\u0000\u0000\u00c0\u00c1\u0005:\u0000\u0000\u00c1"+
		"\u00cc\b\u0002\u0000\u0000\u00c2\u00c3\u0005-\u0000\u0000\u00c3\u00cc"+
		"\b\u0005\u0000\u0000\u00c4\u00c5\u0004\u0017\u0003\u0000\u00c5\u00c6\u0005"+
		"-\u0000\u0000\u00c6\u00cc\u0005-\u0000\u0000\u00c7\u00c8\u0004\u0017\u0004"+
		"\u0000\u00c8\u00c9\u0005-\u0000\u0000\u00c9\u00ca\u0005-\u0000\u0000\u00ca"+
		"\u00cc\u0005-\u0000\u0000\u00cb\u00bf\u0001\u0000\u0000\u0000\u00cb\u00c0"+
		"\u0001\u0000\u0000\u0000\u00cb\u00c2\u0001\u0000\u0000\u0000\u00cb\u00c4"+
		"\u0001\u0000\u0000\u0000\u00cb\u00c7\u0001\u0000\u0000\u0000\u00cc2\u0001"+
		"\u0000\u0000\u0000\u00cd\u00d1\u0003A\u001f\u0000\u00ce\u00d1\u0003C "+
		"\u0000\u00cf\u00d1\u0003E!\u0000\u00d0\u00cd\u0001\u0000\u0000\u0000\u00d0"+
		"\u00ce\u0001\u0000\u0000\u0000\u00d0\u00cf\u0001\u0000\u0000\u0000\u00d1"+
		"\u00d2\u0001\u0000\u0000\u0000\u00d2\u00d3\u0006\u0018\u000b\u0000\u00d3"+
		"4\u0001\u0000\u0000\u0000\u00d4\u00d5\t\u0000\u0000\u0000\u00d56\u0001"+
		"\u0000\u0000\u0000\u00d6\u00d7\u0007\u0006\u0000\u0000\u00d78\u0001\u0000"+
		"\u0000\u0000\u00d8\u00d9\u0007\u0007\u0000\u0000\u00d9:\u0001\u0000\u0000"+
		"\u0000\u00da\u00dc\u0003=\u001d\u0000\u00db\u00da\u0001\u0000\u0000\u0000"+
		"\u00db\u00dc\u0001\u0000\u0000\u0000\u00dc\u00dd\u0001\u0000\u0000\u0000"+
		"\u00dd\u00e2\u0003?\u001e\u0000\u00de\u00df\u0003=\u001d\u0000\u00df\u00e0"+
		"\u0005.\u0000\u0000\u00e0\u00e2\u0001\u0000\u0000\u0000\u00e1\u00db\u0001"+
		"\u0000\u0000\u0000\u00e1\u00de\u0001\u0000\u0000\u0000\u00e2<\u0001\u0000"+
		"\u0000\u0000\u00e3\u00e5\u00039\u001b\u0000\u00e4\u00e3\u0001\u0000\u0000"+
		"\u0000\u00e5\u00e6\u0001\u0000\u0000\u0000\u00e6\u00e4\u0001\u0000\u0000"+
		"\u0000\u00e6\u00e7\u0001\u0000\u0000\u0000\u00e7>\u0001\u0000\u0000\u0000"+
		"\u00e8\u00ea\u0005.\u0000\u0000\u00e9\u00eb\u00039\u001b\u0000\u00ea\u00e9"+
		"\u0001\u0000\u0000\u0000\u00eb\u00ec\u0001\u0000\u0000\u0000\u00ec\u00ea"+
		"\u0001\u0000\u0000\u0000\u00ec\u00ed\u0001\u0000\u0000\u0000\u00ed@\u0001"+
		"\u0000\u0000\u0000\u00ee\u00f0\u0007\b\u0000\u0000\u00ef\u00ee\u0001\u0000"+
		"\u0000\u0000\u00f0\u00f1\u0001\u0000\u0000\u0000\u00f1\u00ef\u0001\u0000"+
		"\u0000\u0000\u00f1\u00f2\u0001\u0000\u0000\u0000\u00f2B\u0001\u0000\u0000"+
		"\u0000\u00f3\u00f7\u0005#\u0000\u0000\u00f4\u00f6\b\t\u0000\u0000\u00f5"+
		"\u00f4\u0001\u0000\u0000\u0000\u00f6\u00f9\u0001\u0000\u0000\u0000\u00f7"+
		"\u00f5\u0001\u0000\u0000\u0000\u00f7\u00f8\u0001\u0000\u0000\u0000\u00f8"+
		"D\u0001\u0000\u0000\u0000\u00f9\u00f7\u0001\u0000\u0000\u0000\u00fa\u00fc"+
		"\u0005\\\u0000\u0000\u00fb\u00fd\u0003A\u001f\u0000\u00fc\u00fb\u0001"+
		"\u0000\u0000\u0000\u00fc\u00fd\u0001\u0000\u0000\u0000\u00fd\u0103\u0001"+
		"\u0000\u0000\u0000\u00fe\u0100\u0005\r\u0000\u0000\u00ff\u00fe\u0001\u0000"+
		"\u0000\u0000\u00ff\u0100\u0001\u0000\u0000\u0000\u0100\u0101\u0001\u0000"+
		"\u0000\u0000\u0101\u0104\u0005\n\u0000\u0000\u0102\u0104\u0005\r\u0000"+
		"\u0000\u0103\u00ff\u0001\u0000\u0000\u0000\u0103\u0102\u0001\u0000\u0000"+
		"\u0000\u0104F\u0001\u0000\u0000\u0000\u0105\u0107\u0005\r\u0000\u0000"+
		"\u0106\u0105\u0001\u0000\u0000\u0000\u0106\u0107\u0001\u0000\u0000\u0000"+
		"\u0107\u0108\u0001\u0000\u0000\u0000\u0108\u010b\u0005\n\u0000\u0000\u0109"+
		"\u010b\u0005\r\u0000\u0000\u010a\u0106\u0001\u0000\u0000\u0000\u010a\u0109"+
		"\u0001\u0000\u0000\u0000\u010b\u010f\u0001\u0000\u0000\u0000\u010c\u010e"+
		"\u0007\b\u0000\u0000\u010d\u010c\u0001\u0000\u0000\u0000\u010e\u0111\u0001"+
		"\u0000\u0000\u0000\u010f\u010d\u0001\u0000\u0000\u0000\u010f\u0110\u0001"+
		"\u0000\u0000\u0000\u0110\u0112\u0001\u0000\u0000\u0000\u0111\u010f\u0001"+
		"\u0000\u0000\u0000\u0112\u0113\u0006\"\f\u0000\u0113H\u0001\u0000\u0000"+
		"\u0000\u0114\u0116\b\t\u0000\u0000\u0115\u0114\u0001\u0000\u0000\u0000"+
		"\u0116\u0119\u0001\u0000\u0000\u0000\u0117\u0115\u0001\u0000\u0000\u0000"+
		"\u0117\u0118\u0001\u0000\u0000\u0000\u0118\u011a\u0001\u0000\u0000\u0000"+
		"\u0119\u0117\u0001\u0000\u0000\u0000\u011a\u011b\u0006#\r\u0000\u011b"+
		"\u011c\u0001\u0000\u0000\u0000\u011c\u011d\u0006#\u000e\u0000\u011dJ\u0001"+
		"\u0000\u0000\u0000\u011e\u0122\b\n\u0000\u0000\u011f\u0120\u0005\\\u0000"+
		"\u0000\u0120\u0122\u0005\\\u0000\u0000\u0121\u011e\u0001\u0000\u0000\u0000"+
		"\u0121\u011f\u0001\u0000\u0000\u0000\u0122\u0125\u0001\u0000\u0000\u0000"+
		"\u0123\u0121\u0001\u0000\u0000\u0000\u0123\u0124\u0001\u0000\u0000\u0000"+
		"\u0124\u0126\u0001\u0000\u0000\u0000\u0125\u0123\u0001\u0000\u0000\u0000"+
		"\u0126\u0127\u0006$\u000e\u0000\u0127L\u0001\u0000\u0000\u0000\u0128\u012a"+
		"\u0005\r\u0000\u0000\u0129\u0128\u0001\u0000\u0000\u0000\u0129\u012a\u0001"+
		"\u0000\u0000\u0000\u012a\u012b\u0001\u0000\u0000\u0000\u012b\u012e\u0005"+
		"\n\u0000\u0000\u012c\u012e\u0005\r\u0000\u0000\u012d\u0129\u0001\u0000"+
		"\u0000\u0000\u012d\u012c\u0001\u0000\u0000\u0000\u012e\u0132\u0001\u0000"+
		"\u0000\u0000\u012f\u0131\u0007\b\u0000\u0000\u0130\u012f\u0001\u0000\u0000"+
		"\u0000\u0131\u0134\u0001\u0000\u0000\u0000\u0132\u0130\u0001\u0000\u0000"+
		"\u0000\u0132\u0133\u0001\u0000\u0000\u0000\u0133N\u0001\u0000\u0000\u0000"+
		"\u0134\u0132\u0001\u0000\u0000\u0000\u0135\u0136\u0005\"\u0000\u0000\u0136"+
		"\u0137\u0001\u0000\u0000\u0000\u0137\u0138\u0006&\u000f\u0000\u0138\u0139"+
		"\u0006&\u0010\u0000\u0139P\u0001\u0000\u0000\u0000\"\u0000\u0001\u0002"+
		"TX[]ekm\u00ae\u00b7\u00ba\u00bc\u00cb\u00d0\u00db\u00e1\u00e6\u00ec\u00f1"+
		"\u00f7\u00fc\u00ff\u0103\u0106\u010a\u010f\u0117\u0121\u0123\u0129\u012d"+
		"\u0132\u0011\u0001\u0000\u0000\u0001\b\u0001\u0001\t\u0002\u0001\f\u0003"+
		"\u0001\r\u0004\u0001\u000e\u0005\u0001\u000f\u0006\u0001\u0010\u0007\u0005"+
		"\u0001\u0000\u0001\u0011\b\u0005\u0002\u0000\u0006\u0000\u0000\u0001\""+
		"\t\u0001#\n\u0007\u0018\u0000\u0007\u0015\u0000\u0004\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}