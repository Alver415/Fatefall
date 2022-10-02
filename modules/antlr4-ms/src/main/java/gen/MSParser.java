// Generated from C:/Users/Alver/Development/projects/Fatefall/modules/antlr4-ms/src/main/antlr4\MSParser.g4 by ANTLR 4.10.1
package gen;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MSParser extends Parser {
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
		RULE_file = 0, RULE_document = 1, RULE_object = 2, RULE_key = 3, RULE_value = 4, 
		RULE_value_scalar = 5, RULE_multiline_string = 6, RULE_string_literal = 7, 
		RULE_string_folded = 8, RULE_string_double_quoted = 9, RULE_mapping = 10, 
		RULE_number = 11, RULE_number_integer = 12, RULE_number_float = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "document", "object", "key", "value", "value_scalar", "multiline_string", 
			"string_literal", "string_folded", "string_double_quoted", "mapping", 
			"number", "number_integer", "number_float"
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

	@Override
	public String getGrammarFileName() { return "MSParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MSParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class FileContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MSParser.EOF, 0); }
		public List<DocumentContext> document() {
			return getRuleContexts(DocumentContext.class);
		}
		public DocumentContext document(int i) {
			return getRuleContext(DocumentContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(MSParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(MSParser.NEWLINE, i);
		}
		public List<TerminalNode> DOCUMENTSTART() { return getTokens(MSParser.DOCUMENTSTART); }
		public TerminalNode DOCUMENTSTART(int i) {
			return getToken(MSParser.DOCUMENTSTART, i);
		}
		public List<TerminalNode> DOCUMENTEND() { return getTokens(MSParser.DOCUMENTEND); }
		public TerminalNode DOCUMENTEND(int i) {
			return getToken(MSParser.DOCUMENTEND, i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).exitFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MSParserVisitor ) return ((MSParserVisitor<? extends T>)visitor).visitFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(28);
					match(NEWLINE);
					}
					} 
				}
				setState(33);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			{
			setState(38);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOCUMENTSTART) {
				{
				setState(34);
				match(DOCUMENTSTART);
				setState(36);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					{
					setState(35);
					match(NEWLINE);
					}
					break;
				}
				}
			}

			setState(40);
			document();
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOCUMENTEND) {
				{
				setState(41);
				match(DOCUMENTEND);
				setState(42);
				match(NEWLINE);
				}
			}

			setState(63);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(48);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NEWLINE) {
						{
						{
						setState(45);
						match(NEWLINE);
						}
						}
						setState(50);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					{
					setState(51);
					match(DOCUMENTSTART);
					setState(52);
					match(NEWLINE);
					}
					setState(54);
					document();
					setState(59);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==DOCUMENTEND) {
						{
						setState(55);
						match(DOCUMENTEND);
						setState(57);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
						case 1:
							{
							setState(56);
							match(NEWLINE);
							}
							break;
						}
						}
					}

					}
					} 
				}
				setState(65);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(66);
				match(NEWLINE);
				}
				}
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(72);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DocumentContext extends ParserRuleContext {
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public DocumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_document; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).enterDocument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).exitDocument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MSParserVisitor ) return ((MSParserVisitor<? extends T>)visitor).visitDocument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DocumentContext document() throws RecognitionException {
		DocumentContext _localctx = new DocumentContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_document);
		try {
			setState(76);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(74);
				object();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				value();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectContext extends ParserRuleContext {
		public List<MappingContext> mapping() {
			return getRuleContexts(MappingContext.class);
		}
		public MappingContext mapping(int i) {
			return getRuleContext(MappingContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(MSParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(MSParser.NEWLINE, i);
		}
		public ObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).enterObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).exitObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MSParserVisitor ) return ((MSParserVisitor<? extends T>)visitor).visitObject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectContext object() throws RecognitionException {
		ObjectContext _localctx = new ObjectContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_object);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(88); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(81);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NEWLINE) {
						{
						{
						setState(78);
						match(NEWLINE);
						}
						}
						setState(83);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(84);
					mapping();
					setState(86);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
					case 1:
						{
						setState(85);
						match(NEWLINE);
						}
						break;
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(90); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeyContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(MSParser.STRING, 0); }
		public KeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_key; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).enterKey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).exitKey(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MSParserVisitor ) return ((MSParserVisitor<? extends T>)visitor).visitKey(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeyContext key() throws RecognitionException {
		KeyContext _localctx = new KeyContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_key);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public Value_scalarContext value_scalar() {
			return getRuleContext(Value_scalarContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(MSParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(MSParser.NEWLINE, i);
		}
		public TerminalNode INDENT() { return getToken(MSParser.INDENT, 0); }
		public TerminalNode DEDENT() { return getToken(MSParser.DEDENT, 0); }
		public Multiline_stringContext multiline_string() {
			return getRuleContext(Multiline_stringContext.class,0);
		}
		public String_literalContext string_literal() {
			return getRuleContext(String_literalContext.class,0);
		}
		public String_foldedContext string_folded() {
			return getRuleContext(String_foldedContext.class,0);
		}
		public String_double_quotedContext string_double_quoted() {
			return getRuleContext(String_double_quotedContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MSParserVisitor ) return ((MSParserVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_value);
		try {
			setState(105);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(94);
				value_scalar();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(95);
				match(NEWLINE);
				setState(96);
				match(INDENT);
				setState(97);
				value_scalar();
				setState(98);
				match(NEWLINE);
				setState(99);
				match(DEDENT);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(101);
				multiline_string();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(102);
				string_literal();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(103);
				string_folded();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(104);
				string_double_quoted();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Value_scalarContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode STRING() { return getToken(MSParser.STRING, 0); }
		public Value_scalarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value_scalar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).enterValue_scalar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).exitValue_scalar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MSParserVisitor ) return ((MSParserVisitor<? extends T>)visitor).visitValue_scalar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Value_scalarContext value_scalar() throws RecognitionException {
		Value_scalarContext _localctx = new Value_scalarContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_value_scalar);
		try {
			setState(109);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECIMAL_INTEGER:
			case FLOAT_NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(107);
				number();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(108);
				match(STRING);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Multiline_stringContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(MSParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(MSParser.NEWLINE, i);
		}
		public TerminalNode INDENT() { return getToken(MSParser.INDENT, 0); }
		public TerminalNode DEDENT() { return getToken(MSParser.DEDENT, 0); }
		public List<Value_scalarContext> value_scalar() {
			return getRuleContexts(Value_scalarContext.class);
		}
		public Value_scalarContext value_scalar(int i) {
			return getRuleContext(Value_scalarContext.class,i);
		}
		public List<Multiline_stringContext> multiline_string() {
			return getRuleContexts(Multiline_stringContext.class);
		}
		public Multiline_stringContext multiline_string(int i) {
			return getRuleContext(Multiline_stringContext.class,i);
		}
		public Multiline_stringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiline_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).enterMultiline_string(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).exitMultiline_string(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MSParserVisitor ) return ((MSParserVisitor<? extends T>)visitor).visitMultiline_string(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Multiline_stringContext multiline_string() throws RecognitionException {
		Multiline_stringContext _localctx = new Multiline_stringContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_multiline_string);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DECIMAL_INTEGER) | (1L << FLOAT_NUMBER) | (1L << STRING))) != 0)) {
				{
				setState(111);
				value_scalar();
				}
			}

			setState(114);
			match(NEWLINE);
			setState(115);
			match(INDENT);
			setState(125); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(125);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						setState(116);
						value_scalar();
						setState(121);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(117);
								match(NEWLINE);
								setState(118);
								value_scalar();
								}
								} 
							}
							setState(123);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
						}
						}
						break;
					case 2:
						{
						setState(124);
						multiline_string();
						}
						break;
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(127); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(129);
				match(NEWLINE);
				}
			}

			setState(132);
			match(DEDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class String_literalContext extends ParserRuleContext {
		public TerminalNode LITERAL_STR_IND() { return getToken(MSParser.LITERAL_STR_IND, 0); }
		public List<TerminalNode> STRING() { return getTokens(MSParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(MSParser.STRING, i);
		}
		public List<TerminalNode> NEWLINE_STR_LITERAL() { return getTokens(MSParser.NEWLINE_STR_LITERAL); }
		public TerminalNode NEWLINE_STR_LITERAL(int i) {
			return getToken(MSParser.NEWLINE_STR_LITERAL, i);
		}
		public String_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).enterString_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).exitString_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MSParserVisitor ) return ((MSParserVisitor<? extends T>)visitor).visitString_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final String_literalContext string_literal() throws RecognitionException {
		String_literalContext _localctx = new String_literalContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_string_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(LITERAL_STR_IND);
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE_STR_LITERAL) {
				{
				{
				setState(136); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(135);
					match(NEWLINE_STR_LITERAL);
					}
					}
					setState(138); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE_STR_LITERAL );
				setState(140);
				match(STRING);
				}
				}
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class String_foldedContext extends ParserRuleContext {
		public TerminalNode FOLD_STR_IND() { return getToken(MSParser.FOLD_STR_IND, 0); }
		public List<TerminalNode> STRING() { return getTokens(MSParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(MSParser.STRING, i);
		}
		public List<TerminalNode> NEWLINE_STR_LITERAL() { return getTokens(MSParser.NEWLINE_STR_LITERAL); }
		public TerminalNode NEWLINE_STR_LITERAL(int i) {
			return getToken(MSParser.NEWLINE_STR_LITERAL, i);
		}
		public String_foldedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_folded; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).enterString_folded(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).exitString_folded(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MSParserVisitor ) return ((MSParserVisitor<? extends T>)visitor).visitString_folded(this);
			else return visitor.visitChildren(this);
		}
	}

	public final String_foldedContext string_folded() throws RecognitionException {
		String_foldedContext _localctx = new String_foldedContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_string_folded);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(FOLD_STR_IND);
			setState(155);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE_STR_LITERAL) {
				{
				{
				setState(148); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(147);
					match(NEWLINE_STR_LITERAL);
					}
					}
					setState(150); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE_STR_LITERAL );
				setState(152);
				match(STRING);
				}
				}
				setState(157);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class String_double_quotedContext extends ParserRuleContext {
		public List<TerminalNode> DOUBLE_QUOTE() { return getTokens(MSParser.DOUBLE_QUOTE); }
		public TerminalNode DOUBLE_QUOTE(int i) {
			return getToken(MSParser.DOUBLE_QUOTE, i);
		}
		public List<TerminalNode> STRING() { return getTokens(MSParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(MSParser.STRING, i);
		}
		public List<TerminalNode> NEWLINE_STR_QUOTE() { return getTokens(MSParser.NEWLINE_STR_QUOTE); }
		public TerminalNode NEWLINE_STR_QUOTE(int i) {
			return getToken(MSParser.NEWLINE_STR_QUOTE, i);
		}
		public String_double_quotedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_double_quoted; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).enterString_double_quoted(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).exitString_double_quoted(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MSParserVisitor ) return ((MSParserVisitor<? extends T>)visitor).visitString_double_quoted(this);
			else return visitor.visitChildren(this);
		}
	}

	public final String_double_quotedContext string_double_quoted() throws RecognitionException {
		String_double_quotedContext _localctx = new String_double_quotedContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_string_double_quoted);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			match(DOUBLE_QUOTE);
			setState(159);
			match(STRING);
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE_STR_QUOTE) {
				{
				{
				setState(161); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(160);
					match(NEWLINE_STR_QUOTE);
					}
					}
					setState(163); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE_STR_QUOTE );
				setState(165);
				match(STRING);
				}
				}
				setState(170);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(171);
			match(DOUBLE_QUOTE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MappingContext extends ParserRuleContext {
		public KeyContext key() {
			return getRuleContext(KeyContext.class,0);
		}
		public TerminalNode COLON() { return getToken(MSParser.COLON, 0); }
		public TerminalNode INDENT() { return getToken(MSParser.INDENT, 0); }
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public TerminalNode DEDENT() { return getToken(MSParser.DEDENT, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(MSParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(MSParser.NEWLINE, i);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public MappingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mapping; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).enterMapping(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).exitMapping(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MSParserVisitor ) return ((MSParserVisitor<? extends T>)visitor).visitMapping(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MappingContext mapping() throws RecognitionException {
		MappingContext _localctx = new MappingContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_mapping);
		int _la;
		try {
			setState(189);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(173);
				key();
				setState(174);
				match(COLON);
				setState(176); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(175);
					match(NEWLINE);
					}
					}
					setState(178); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				setState(180);
				match(INDENT);
				setState(181);
				object();
				setState(182);
				match(DEDENT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(184);
				key();
				setState(185);
				match(COLON);
				setState(187);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(186);
					value();
					}
					break;
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public Number_integerContext number_integer() {
			return getRuleContext(Number_integerContext.class,0);
		}
		public Number_floatContext number_float() {
			return getRuleContext(Number_floatContext.class,0);
		}
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MSParserVisitor ) return ((MSParserVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_number);
		try {
			setState(193);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECIMAL_INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(191);
				number_integer();
				}
				break;
			case FLOAT_NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(192);
				number_float();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Number_integerContext extends ParserRuleContext {
		public TerminalNode DECIMAL_INTEGER() { return getToken(MSParser.DECIMAL_INTEGER, 0); }
		public Number_integerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number_integer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).enterNumber_integer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).exitNumber_integer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MSParserVisitor ) return ((MSParserVisitor<? extends T>)visitor).visitNumber_integer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Number_integerContext number_integer() throws RecognitionException {
		Number_integerContext _localctx = new Number_integerContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_number_integer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			match(DECIMAL_INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Number_floatContext extends ParserRuleContext {
		public TerminalNode FLOAT_NUMBER() { return getToken(MSParser.FLOAT_NUMBER, 0); }
		public Number_floatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number_float; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).enterNumber_float(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSParserListener ) ((MSParserListener)listener).exitNumber_float(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MSParserVisitor ) return ((MSParserVisitor<? extends T>)visitor).visitNumber_float(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Number_floatContext number_float() throws RecognitionException {
		Number_floatContext _localctx = new Number_floatContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_number_float);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			match(FLOAT_NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u001c\u00c8\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0005\u0000\u001e\b\u0000"+
		"\n\u0000\f\u0000!\t\u0000\u0001\u0000\u0001\u0000\u0003\u0000%\b\u0000"+
		"\u0003\u0000\'\b\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000"+
		",\b\u0000\u0001\u0000\u0005\u0000/\b\u0000\n\u0000\f\u00002\t\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0003"+
		"\u0000:\b\u0000\u0003\u0000<\b\u0000\u0005\u0000>\b\u0000\n\u0000\f\u0000"+
		"A\t\u0000\u0001\u0000\u0005\u0000D\b\u0000\n\u0000\f\u0000G\t\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0003\u0001M\b\u0001\u0001"+
		"\u0002\u0005\u0002P\b\u0002\n\u0002\f\u0002S\t\u0002\u0001\u0002\u0001"+
		"\u0002\u0003\u0002W\b\u0002\u0004\u0002Y\b\u0002\u000b\u0002\f\u0002Z"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0003\u0004j\b\u0004\u0001\u0005\u0001\u0005\u0003\u0005"+
		"n\b\u0005\u0001\u0006\u0003\u0006q\b\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0005\u0006x\b\u0006\n\u0006\f\u0006{\t"+
		"\u0006\u0001\u0006\u0004\u0006~\b\u0006\u000b\u0006\f\u0006\u007f\u0001"+
		"\u0006\u0003\u0006\u0083\b\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001"+
		"\u0007\u0004\u0007\u0089\b\u0007\u000b\u0007\f\u0007\u008a\u0001\u0007"+
		"\u0005\u0007\u008e\b\u0007\n\u0007\f\u0007\u0091\t\u0007\u0001\b\u0001"+
		"\b\u0004\b\u0095\b\b\u000b\b\f\b\u0096\u0001\b\u0005\b\u009a\b\b\n\b\f"+
		"\b\u009d\t\b\u0001\t\u0001\t\u0001\t\u0004\t\u00a2\b\t\u000b\t\f\t\u00a3"+
		"\u0001\t\u0005\t\u00a7\b\t\n\t\f\t\u00aa\t\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0004\n\u00b1\b\n\u000b\n\f\n\u00b2\u0001\n\u0001\n\u0001\n"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00bc\b\n\u0003\n\u00be\b\n\u0001"+
		"\u000b\u0001\u000b\u0003\u000b\u00c2\b\u000b\u0001\f\u0001\f\u0001\r\u0001"+
		"\r\u0001\r\u0000\u0000\u000e\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u0000\u0000\u00db\u0000\u001f\u0001\u0000"+
		"\u0000\u0000\u0002L\u0001\u0000\u0000\u0000\u0004X\u0001\u0000\u0000\u0000"+
		"\u0006\\\u0001\u0000\u0000\u0000\bi\u0001\u0000\u0000\u0000\nm\u0001\u0000"+
		"\u0000\u0000\fp\u0001\u0000\u0000\u0000\u000e\u0086\u0001\u0000\u0000"+
		"\u0000\u0010\u0092\u0001\u0000\u0000\u0000\u0012\u009e\u0001\u0000\u0000"+
		"\u0000\u0014\u00bd\u0001\u0000\u0000\u0000\u0016\u00c1\u0001\u0000\u0000"+
		"\u0000\u0018\u00c3\u0001\u0000\u0000\u0000\u001a\u00c5\u0001\u0000\u0000"+
		"\u0000\u001c\u001e\u0005\u0003\u0000\u0000\u001d\u001c\u0001\u0000\u0000"+
		"\u0000\u001e!\u0001\u0000\u0000\u0000\u001f\u001d\u0001\u0000\u0000\u0000"+
		"\u001f \u0001\u0000\u0000\u0000 &\u0001\u0000\u0000\u0000!\u001f\u0001"+
		"\u0000\u0000\u0000\"$\u0005\u0007\u0000\u0000#%\u0005\u0003\u0000\u0000"+
		"$#\u0001\u0000\u0000\u0000$%\u0001\u0000\u0000\u0000%\'\u0001\u0000\u0000"+
		"\u0000&\"\u0001\u0000\u0000\u0000&\'\u0001\u0000\u0000\u0000\'(\u0001"+
		"\u0000\u0000\u0000(+\u0003\u0002\u0001\u0000)*\u0005\b\u0000\u0000*,\u0005"+
		"\u0003\u0000\u0000+)\u0001\u0000\u0000\u0000+,\u0001\u0000\u0000\u0000"+
		",?\u0001\u0000\u0000\u0000-/\u0005\u0003\u0000\u0000.-\u0001\u0000\u0000"+
		"\u0000/2\u0001\u0000\u0000\u00000.\u0001\u0000\u0000\u000001\u0001\u0000"+
		"\u0000\u000013\u0001\u0000\u0000\u000020\u0001\u0000\u0000\u000034\u0005"+
		"\u0007\u0000\u000045\u0005\u0003\u0000\u000056\u0001\u0000\u0000\u0000"+
		"6;\u0003\u0002\u0001\u000079\u0005\b\u0000\u00008:\u0005\u0003\u0000\u0000"+
		"98\u0001\u0000\u0000\u00009:\u0001\u0000\u0000\u0000:<\u0001\u0000\u0000"+
		"\u0000;7\u0001\u0000\u0000\u0000;<\u0001\u0000\u0000\u0000<>\u0001\u0000"+
		"\u0000\u0000=0\u0001\u0000\u0000\u0000>A\u0001\u0000\u0000\u0000?=\u0001"+
		"\u0000\u0000\u0000?@\u0001\u0000\u0000\u0000@E\u0001\u0000\u0000\u0000"+
		"A?\u0001\u0000\u0000\u0000BD\u0005\u0003\u0000\u0000CB\u0001\u0000\u0000"+
		"\u0000DG\u0001\u0000\u0000\u0000EC\u0001\u0000\u0000\u0000EF\u0001\u0000"+
		"\u0000\u0000FH\u0001\u0000\u0000\u0000GE\u0001\u0000\u0000\u0000HI\u0005"+
		"\u0000\u0000\u0001I\u0001\u0001\u0000\u0000\u0000JM\u0003\u0004\u0002"+
		"\u0000KM\u0003\b\u0004\u0000LJ\u0001\u0000\u0000\u0000LK\u0001\u0000\u0000"+
		"\u0000M\u0003\u0001\u0000\u0000\u0000NP\u0005\u0003\u0000\u0000ON\u0001"+
		"\u0000\u0000\u0000PS\u0001\u0000\u0000\u0000QO\u0001\u0000\u0000\u0000"+
		"QR\u0001\u0000\u0000\u0000RT\u0001\u0000\u0000\u0000SQ\u0001\u0000\u0000"+
		"\u0000TV\u0003\u0014\n\u0000UW\u0005\u0003\u0000\u0000VU\u0001\u0000\u0000"+
		"\u0000VW\u0001\u0000\u0000\u0000WY\u0001\u0000\u0000\u0000XQ\u0001\u0000"+
		"\u0000\u0000YZ\u0001\u0000\u0000\u0000ZX\u0001\u0000\u0000\u0000Z[\u0001"+
		"\u0000\u0000\u0000[\u0005\u0001\u0000\u0000\u0000\\]\u0005\u0018\u0000"+
		"\u0000]\u0007\u0001\u0000\u0000\u0000^j\u0003\n\u0005\u0000_`\u0005\u0003"+
		"\u0000\u0000`a\u0005\u0001\u0000\u0000ab\u0003\n\u0005\u0000bc\u0005\u0003"+
		"\u0000\u0000cd\u0005\u0002\u0000\u0000dj\u0001\u0000\u0000\u0000ej\u0003"+
		"\f\u0006\u0000fj\u0003\u000e\u0007\u0000gj\u0003\u0010\b\u0000hj\u0003"+
		"\u0012\t\u0000i^\u0001\u0000\u0000\u0000i_\u0001\u0000\u0000\u0000ie\u0001"+
		"\u0000\u0000\u0000if\u0001\u0000\u0000\u0000ig\u0001\u0000\u0000\u0000"+
		"ih\u0001\u0000\u0000\u0000j\t\u0001\u0000\u0000\u0000kn\u0003\u0016\u000b"+
		"\u0000ln\u0005\u0018\u0000\u0000mk\u0001\u0000\u0000\u0000ml\u0001\u0000"+
		"\u0000\u0000n\u000b\u0001\u0000\u0000\u0000oq\u0003\n\u0005\u0000po\u0001"+
		"\u0000\u0000\u0000pq\u0001\u0000\u0000\u0000qr\u0001\u0000\u0000\u0000"+
		"rs\u0005\u0003\u0000\u0000s}\u0005\u0001\u0000\u0000ty\u0003\n\u0005\u0000"+
		"uv\u0005\u0003\u0000\u0000vx\u0003\n\u0005\u0000wu\u0001\u0000\u0000\u0000"+
		"x{\u0001\u0000\u0000\u0000yw\u0001\u0000\u0000\u0000yz\u0001\u0000\u0000"+
		"\u0000z~\u0001\u0000\u0000\u0000{y\u0001\u0000\u0000\u0000|~\u0003\f\u0006"+
		"\u0000}t\u0001\u0000\u0000\u0000}|\u0001\u0000\u0000\u0000~\u007f\u0001"+
		"\u0000\u0000\u0000\u007f}\u0001\u0000\u0000\u0000\u007f\u0080\u0001\u0000"+
		"\u0000\u0000\u0080\u0082\u0001\u0000\u0000\u0000\u0081\u0083\u0005\u0003"+
		"\u0000\u0000\u0082\u0081\u0001\u0000\u0000\u0000\u0082\u0083\u0001\u0000"+
		"\u0000\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084\u0085\u0005\u0002"+
		"\u0000\u0000\u0085\r\u0001\u0000\u0000\u0000\u0086\u008f\u0005\u0013\u0000"+
		"\u0000\u0087\u0089\u0005\u001b\u0000\u0000\u0088\u0087\u0001\u0000\u0000"+
		"\u0000\u0089\u008a\u0001\u0000\u0000\u0000\u008a\u0088\u0001\u0000\u0000"+
		"\u0000\u008a\u008b\u0001\u0000\u0000\u0000\u008b\u008c\u0001\u0000\u0000"+
		"\u0000\u008c\u008e\u0005\u0018\u0000\u0000\u008d\u0088\u0001\u0000\u0000"+
		"\u0000\u008e\u0091\u0001\u0000\u0000\u0000\u008f\u008d\u0001\u0000\u0000"+
		"\u0000\u008f\u0090\u0001\u0000\u0000\u0000\u0090\u000f\u0001\u0000\u0000"+
		"\u0000\u0091\u008f\u0001\u0000\u0000\u0000\u0092\u009b\u0005\u0014\u0000"+
		"\u0000\u0093\u0095\u0005\u001b\u0000\u0000\u0094\u0093\u0001\u0000\u0000"+
		"\u0000\u0095\u0096\u0001\u0000\u0000\u0000\u0096\u0094\u0001\u0000\u0000"+
		"\u0000\u0096\u0097\u0001\u0000\u0000\u0000\u0097\u0098\u0001\u0000\u0000"+
		"\u0000\u0098\u009a\u0005\u0018\u0000\u0000\u0099\u0094\u0001\u0000\u0000"+
		"\u0000\u009a\u009d\u0001\u0000\u0000\u0000\u009b\u0099\u0001\u0000\u0000"+
		"\u0000\u009b\u009c\u0001\u0000\u0000\u0000\u009c\u0011\u0001\u0000\u0000"+
		"\u0000\u009d\u009b\u0001\u0000\u0000\u0000\u009e\u009f\u0005\u0015\u0000"+
		"\u0000\u009f\u00a8\u0005\u0018\u0000\u0000\u00a0\u00a2\u0005\u001c\u0000"+
		"\u0000\u00a1\u00a0\u0001\u0000\u0000\u0000\u00a2\u00a3\u0001\u0000\u0000"+
		"\u0000\u00a3\u00a1\u0001\u0000\u0000\u0000\u00a3\u00a4\u0001\u0000\u0000"+
		"\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5\u00a7\u0005\u0018\u0000"+
		"\u0000\u00a6\u00a1\u0001\u0000\u0000\u0000\u00a7\u00aa\u0001\u0000\u0000"+
		"\u0000\u00a8\u00a6\u0001\u0000\u0000\u0000\u00a8\u00a9\u0001\u0000\u0000"+
		"\u0000\u00a9\u00ab\u0001\u0000\u0000\u0000\u00aa\u00a8\u0001\u0000\u0000"+
		"\u0000\u00ab\u00ac\u0005\u0015\u0000\u0000\u00ac\u0013\u0001\u0000\u0000"+
		"\u0000\u00ad\u00ae\u0003\u0006\u0003\u0000\u00ae\u00b0\u0005\u000e\u0000"+
		"\u0000\u00af\u00b1\u0005\u0003\u0000\u0000\u00b0\u00af\u0001\u0000\u0000"+
		"\u0000\u00b1\u00b2\u0001\u0000\u0000\u0000\u00b2\u00b0\u0001\u0000\u0000"+
		"\u0000\u00b2\u00b3\u0001\u0000\u0000\u0000\u00b3\u00b4\u0001\u0000\u0000"+
		"\u0000\u00b4\u00b5\u0005\u0001\u0000\u0000\u00b5\u00b6\u0003\u0004\u0002"+
		"\u0000\u00b6\u00b7\u0005\u0002\u0000\u0000\u00b7\u00be\u0001\u0000\u0000"+
		"\u0000\u00b8\u00b9\u0003\u0006\u0003\u0000\u00b9\u00bb\u0005\u000e\u0000"+
		"\u0000\u00ba\u00bc\u0003\b\u0004\u0000\u00bb\u00ba\u0001\u0000\u0000\u0000"+
		"\u00bb\u00bc\u0001\u0000\u0000\u0000\u00bc\u00be\u0001\u0000\u0000\u0000"+
		"\u00bd\u00ad\u0001\u0000\u0000\u0000\u00bd\u00b8\u0001\u0000\u0000\u0000"+
		"\u00be\u0015\u0001\u0000\u0000\u0000\u00bf\u00c2\u0003\u0018\f\u0000\u00c0"+
		"\u00c2\u0003\u001a\r\u0000\u00c1\u00bf\u0001\u0000\u0000\u0000\u00c1\u00c0"+
		"\u0001\u0000\u0000\u0000\u00c2\u0017\u0001\u0000\u0000\u0000\u00c3\u00c4"+
		"\u0005\u0004\u0000\u0000\u00c4\u0019\u0001\u0000\u0000\u0000\u00c5\u00c6"+
		"\u0005\u0005\u0000\u0000\u00c6\u001b\u0001\u0000\u0000\u0000\u001e\u001f"+
		"$&+09;?ELQVZimpy}\u007f\u0082\u008a\u008f\u0096\u009b\u00a3\u00a8\u00b2"+
		"\u00bb\u00bd\u00c1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}