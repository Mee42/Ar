// Generated from /home/carson/java/workspace/ar/src/main/antlr4/Ar.g4 by ANTLR 4.8
package dev.mee42.antlr4;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ArParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, INTEGER=8, IDENTIFIER=9, 
		TYPE_IDENTIFIER=10, WHITESPACE=11;
	public static final int
		RULE_main = 0, RULE_definition = 1, RULE_typeDef = 2, RULE_type = 3, RULE_value = 4, 
		RULE_lambda = 5;
	private static String[] makeRuleNames() {
		return new String[] {
			"main", "definition", "typeDef", "type", "value", "lambda"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'='", "'=>'", "'->'", "'('", "')'", "'\\'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, "INTEGER", "IDENTIFIER", 
			"TYPE_IDENTIFIER", "WHITESPACE"
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
	public String getGrammarFileName() { return "Ar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ArParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class MainContext extends ParserRuleContext {
		public List<DefinitionContext> definition() {
			return getRuleContexts(DefinitionContext.class);
		}
		public DefinitionContext definition(int i) {
			return getRuleContext(DefinitionContext.class,i);
		}
		public List<TypeDefContext> typeDef() {
			return getRuleContexts(TypeDefContext.class);
		}
		public TypeDefContext typeDef(int i) {
			return getRuleContext(TypeDefContext.class,i);
		}
		public MainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_main; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArListener ) ((ArListener)listener).enterMain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArListener ) ((ArListener)listener).exitMain(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArVisitor ) return ((ArVisitor<? extends T>)visitor).visitMain(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MainContext main() throws RecognitionException {
		MainContext _localctx = new MainContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_main);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENTIFIER) {
				{
				{
				setState(14);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(12);
					definition();
					}
					break;
				case 2:
					{
					setState(13);
					typeDef();
					}
					break;
				}
				setState(17);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(16);
					match(T__0);
					}
				}

				}
				}
				setState(23);
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

	public static class DefinitionContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(ArParser.IDENTIFIER, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public DefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArListener ) ((ArListener)listener).enterDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArListener ) ((ArListener)listener).exitDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArVisitor ) return ((ArVisitor<? extends T>)visitor).visitDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefinitionContext definition() throws RecognitionException {
		DefinitionContext _localctx = new DefinitionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_definition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			match(IDENTIFIER);
			setState(25);
			match(T__1);
			setState(26);
			value(0);
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

	public static class TypeDefContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(ArParser.IDENTIFIER, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArListener ) ((ArListener)listener).enterTypeDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArListener ) ((ArListener)listener).exitTypeDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArVisitor ) return ((ArVisitor<? extends T>)visitor).visitTypeDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeDefContext typeDef() throws RecognitionException {
		TypeDefContext _localctx = new TypeDefContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_typeDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			match(IDENTIFIER);
			setState(29);
			match(T__2);
			setState(30);
			type(0);
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

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	 
		public TypeContext() { }
		public void copyFrom(TypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FunctionalTypeContext extends TypeContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public FunctionalTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArListener ) ((ArListener)listener).enterFunctionalType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArListener ) ((ArListener)listener).exitFunctionalType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArVisitor ) return ((ArVisitor<? extends T>)visitor).visitFunctionalType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RawTypeContext extends TypeContext {
		public TerminalNode TYPE_IDENTIFIER() { return getToken(ArParser.TYPE_IDENTIFIER, 0); }
		public RawTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArListener ) ((ArListener)listener).enterRawType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArListener ) ((ArListener)listener).exitRawType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArVisitor ) return ((ArVisitor<? extends T>)visitor).visitRawType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenthesesTypeContext extends TypeContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ParenthesesTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArListener ) ((ArListener)listener).enterParenthesesType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArListener ) ((ArListener)listener).exitParenthesesType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArVisitor ) return ((ArVisitor<? extends T>)visitor).visitParenthesesType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		return type(0);
	}

	private TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState);
		TypeContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE_IDENTIFIER:
				{
				_localctx = new RawTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(33);
				match(TYPE_IDENTIFIER);
				}
				break;
			case T__4:
				{
				_localctx = new ParenthesesTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(34);
				match(T__4);
				setState(35);
				type(0);
				setState(36);
				match(T__5);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(45);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new FunctionalTypeContext(new TypeContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(40);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(41);
					match(T__3);
					setState(42);
					type(4);
					}
					} 
				}
				setState(47);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
	 
		public ValueContext() { }
		public void copyFrom(ValueContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IntegerValueContext extends ValueContext {
		public TerminalNode INTEGER() { return getToken(ArParser.INTEGER, 0); }
		public IntegerValueContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArListener ) ((ArListener)listener).enterIntegerValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArListener ) ((ArListener)listener).exitIntegerValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArVisitor ) return ((ArVisitor<? extends T>)visitor).visitIntegerValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VariableCallValueContext extends ValueContext {
		public TerminalNode IDENTIFIER() { return getToken(ArParser.IDENTIFIER, 0); }
		public VariableCallValueContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArListener ) ((ArListener)listener).enterVariableCallValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArListener ) ((ArListener)listener).exitVariableCallValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArVisitor ) return ((ArVisitor<? extends T>)visitor).visitVariableCallValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunctionApplicationValueContext extends ValueContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public FunctionApplicationValueContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArListener ) ((ArListener)listener).enterFunctionApplicationValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArListener ) ((ArListener)listener).exitFunctionApplicationValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArVisitor ) return ((ArVisitor<? extends T>)visitor).visitFunctionApplicationValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LambdaValueContext extends ValueContext {
		public LambdaContext lambda() {
			return getRuleContext(LambdaContext.class,0);
		}
		public LambdaValueContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArListener ) ((ArListener)listener).enterLambdaValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArListener ) ((ArListener)listener).exitLambdaValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArVisitor ) return ((ArVisitor<? extends T>)visitor).visitLambdaValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenthesesValueContext extends ValueContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ParenthesesValueContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArListener ) ((ArListener)listener).enterParenthesesValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArListener ) ((ArListener)listener).exitParenthesesValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArVisitor ) return ((ArVisitor<? extends T>)visitor).visitParenthesesValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		return value(0);
	}

	private ValueContext value(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ValueContext _localctx = new ValueContext(_ctx, _parentState);
		ValueContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_value, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				_localctx = new IntegerValueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(49);
				match(INTEGER);
				}
				break;
			case 2:
				{
				_localctx = new VariableCallValueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(50);
				match(IDENTIFIER);
				}
				break;
			case 3:
				{
				_localctx = new ParenthesesValueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(51);
				match(T__4);
				setState(52);
				value(0);
				setState(53);
				match(T__5);
				}
				break;
			case 4:
				{
				_localctx = new LambdaValueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(55);
				lambda();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(62);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new FunctionApplicationValueContext(new ValueContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_value);
					setState(58);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(59);
					value(4);
					}
					} 
				}
				setState(64);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class LambdaContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(ArParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(ArParser.IDENTIFIER, i);
		}
		public LambdaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambda; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArListener ) ((ArListener)listener).enterLambda(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArListener ) ((ArListener)listener).exitLambda(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArVisitor ) return ((ArVisitor<? extends T>)visitor).visitLambda(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaContext lambda() throws RecognitionException {
		LambdaContext _localctx = new LambdaContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_lambda);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(T__4);
			setState(67); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(66);
				match(IDENTIFIER);
				}
				}
				setState(69); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(71);
			match(T__2);
			setState(72);
			type(0);
			setState(73);
			match(T__6);
			setState(74);
			value(0);
			setState(75);
			match(T__5);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return type_sempred((TypeContext)_localctx, predIndex);
		case 4:
			return value_sempred((ValueContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean value_sempred(ValueContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\rP\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\5\2\21\n\2\3\2\5\2\24\n\2\7"+
		"\2\26\n\2\f\2\16\2\31\13\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\5\5)\n\5\3\5\3\5\3\5\7\5.\n\5\f\5\16\5\61\13\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\5\6;\n\6\3\6\3\6\7\6?\n\6\f\6\16\6B\13\6\3\7\3"+
		"\7\6\7F\n\7\r\7\16\7G\3\7\3\7\3\7\3\7\3\7\3\7\3\7\2\4\b\n\b\2\4\6\b\n"+
		"\f\2\2\2S\2\27\3\2\2\2\4\32\3\2\2\2\6\36\3\2\2\2\b(\3\2\2\2\n:\3\2\2\2"+
		"\fC\3\2\2\2\16\21\5\4\3\2\17\21\5\6\4\2\20\16\3\2\2\2\20\17\3\2\2\2\21"+
		"\23\3\2\2\2\22\24\7\3\2\2\23\22\3\2\2\2\23\24\3\2\2\2\24\26\3\2\2\2\25"+
		"\20\3\2\2\2\26\31\3\2\2\2\27\25\3\2\2\2\27\30\3\2\2\2\30\3\3\2\2\2\31"+
		"\27\3\2\2\2\32\33\7\13\2\2\33\34\7\4\2\2\34\35\5\n\6\2\35\5\3\2\2\2\36"+
		"\37\7\13\2\2\37 \7\5\2\2 !\5\b\5\2!\7\3\2\2\2\"#\b\5\1\2#)\7\f\2\2$%\7"+
		"\7\2\2%&\5\b\5\2&\'\7\b\2\2\')\3\2\2\2(\"\3\2\2\2($\3\2\2\2)/\3\2\2\2"+
		"*+\f\5\2\2+,\7\6\2\2,.\5\b\5\6-*\3\2\2\2.\61\3\2\2\2/-\3\2\2\2/\60\3\2"+
		"\2\2\60\t\3\2\2\2\61/\3\2\2\2\62\63\b\6\1\2\63;\7\n\2\2\64;\7\13\2\2\65"+
		"\66\7\7\2\2\66\67\5\n\6\2\678\7\b\2\28;\3\2\2\29;\5\f\7\2:\62\3\2\2\2"+
		":\64\3\2\2\2:\65\3\2\2\2:9\3\2\2\2;@\3\2\2\2<=\f\5\2\2=?\5\n\6\6><\3\2"+
		"\2\2?B\3\2\2\2@>\3\2\2\2@A\3\2\2\2A\13\3\2\2\2B@\3\2\2\2CE\7\7\2\2DF\7"+
		"\13\2\2ED\3\2\2\2FG\3\2\2\2GE\3\2\2\2GH\3\2\2\2HI\3\2\2\2IJ\7\5\2\2JK"+
		"\5\b\5\2KL\7\t\2\2LM\5\n\6\2MN\7\b\2\2N\r\3\2\2\2\n\20\23\27(/:@G";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}