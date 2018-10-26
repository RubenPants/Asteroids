// Generated from AsteroidsProgram.g4 by ANTLR 4.7

    package asteroids.part3.programs.internal.generated;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AsteroidsProgramParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, NULL=2, SELF=3, SHIP=4, ASTEROID=5, PLANETOID=6, BULLET=7, PLANET=8, 
		ANY=9, GETRADIUS=10, GETX=11, GETY=12, GETVX=13, GETVY=14, GETDIR=15, 
		SQRT=16, NOT=17, THRUSTON=18, THRUSTOFF=19, TURN=20, FIRE=21, SKIP_ACTION=22, 
		DEF=23, IF=24, THEN=25, ELSE=26, WHILE=27, BREAK=28, RETURN=29, PRINT=30, 
		NOTHING=31, ASSIGN=32, MUL=33, ADD=34, SUB=35, EQ=36, LT=37, NUMBER=38, 
		FLOAT=39, INTEGER=40, IDENTIFIER=41, PARAM=42, COMMA=43, DOLLAR=44, LEFT_PAREN=45, 
		RIGHT_PAREN=46, LEFT_BRACE=47, RIGHT_BRACE=48, WHITESPACE=49, SINGLE_COMMENT=50;
	public static final int
		RULE_program = 0, RULE_functiondef = 1, RULE_statement = 2, RULE_assignmentStatement = 3, 
		RULE_whileStatement = 4, RULE_breakStatement = 5, RULE_returnStatement = 6, 
		RULE_ifStatement = 7, RULE_printStatement = 8, RULE_statementSequence = 9, 
		RULE_actionStatement = 10, RULE_expression = 11, RULE_entity = 12, RULE_functionCall = 13;
	public static final String[] ruleNames = {
		"program", "functiondef", "statement", "assignmentStatement", "whileStatement", 
		"breakStatement", "returnStatement", "ifStatement", "printStatement", 
		"statementSequence", "actionStatement", "expression", "entity", "functionCall"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "'null'", "'self'", "'ship'", "'asteroid'", "'planetoid'", 
		"'bullet'", "'planet'", "'any'", "'getradius'", "'getx'", "'gety'", "'getvx'", 
		"'getvy'", "'getdir'", "'sqrt'", "'!'", "'thrust'", "'thrust_off'", "'turn'", 
		"'fire'", "'skip'", "'def'", "'if'", "'then'", "'else'", "'while'", "'break'", 
		"'return'", "'print'", "'nothing'", "':='", "'*'", "'+'", "'-'", "'=='", 
		"'<'", null, null, null, null, null, "','", "'$'", "'('", "')'", "'{'", 
		"'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, "NULL", "SELF", "SHIP", "ASTEROID", "PLANETOID", "BULLET", 
		"PLANET", "ANY", "GETRADIUS", "GETX", "GETY", "GETVX", "GETVY", "GETDIR", 
		"SQRT", "NOT", "THRUSTON", "THRUSTOFF", "TURN", "FIRE", "SKIP_ACTION", 
		"DEF", "IF", "THEN", "ELSE", "WHILE", "BREAK", "RETURN", "PRINT", "NOTHING", 
		"ASSIGN", "MUL", "ADD", "SUB", "EQ", "LT", "NUMBER", "FLOAT", "INTEGER", 
		"IDENTIFIER", "PARAM", "COMMA", "DOLLAR", "LEFT_PAREN", "RIGHT_PAREN", 
		"LEFT_BRACE", "RIGHT_BRACE", "WHITESPACE", "SINGLE_COMMENT"
	};
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
	public String getGrammarFileName() { return "AsteroidsProgram.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AsteroidsProgramParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public FunctiondefContext functiondef;
		public List<FunctiondefContext> funcdef = new ArrayList<FunctiondefContext>();
		public StatementSequenceContext programBody;
		public StatementSequenceContext statementSequence() {
			return getRuleContext(StatementSequenceContext.class,0);
		}
		public List<FunctiondefContext> functiondef() {
			return getRuleContexts(FunctiondefContext.class);
		}
		public FunctiondefContext functiondef(int i) {
			return getRuleContext(FunctiondefContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DEF) {
				{
				{
				setState(28);
				((ProgramContext)_localctx).functiondef = functiondef();
				((ProgramContext)_localctx).funcdef.add(((ProgramContext)_localctx).functiondef);
				}
				}
				setState(33);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(34);
			((ProgramContext)_localctx).programBody = statementSequence();
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

	public static class FunctiondefContext extends ParserRuleContext {
		public Token funcname;
		public StatementSequenceContext body;
		public TerminalNode DEF() { return getToken(AsteroidsProgramParser.DEF, 0); }
		public TerminalNode LEFT_BRACE() { return getToken(AsteroidsProgramParser.LEFT_BRACE, 0); }
		public TerminalNode RIGHT_BRACE() { return getToken(AsteroidsProgramParser.RIGHT_BRACE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(AsteroidsProgramParser.IDENTIFIER, 0); }
		public StatementSequenceContext statementSequence() {
			return getRuleContext(StatementSequenceContext.class,0);
		}
		public FunctiondefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functiondef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterFunctiondef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitFunctiondef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitFunctiondef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctiondefContext functiondef() throws RecognitionException {
		FunctiondefContext _localctx = new FunctiondefContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_functiondef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(DEF);
			setState(37);
			((FunctiondefContext)_localctx).funcname = match(IDENTIFIER);
			setState(38);
			match(LEFT_BRACE);
			setState(39);
			((FunctiondefContext)_localctx).body = statementSequence();
			setState(40);
			match(RIGHT_BRACE);
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

	public static class StatementContext extends ParserRuleContext {
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public PrintStatementContext printStatement() {
			return getRuleContext(PrintStatementContext.class,0);
		}
		public ActionStatementContext actionStatement() {
			return getRuleContext(ActionStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		try {
			setState(49);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(42);
				assignmentStatement();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 2);
				{
				setState(43);
				whileStatement();
				}
				break;
			case BREAK:
				enterOuterAlt(_localctx, 3);
				{
				setState(44);
				breakStatement();
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 4);
				{
				setState(45);
				returnStatement();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 5);
				{
				setState(46);
				ifStatement();
				}
				break;
			case PRINT:
				enterOuterAlt(_localctx, 6);
				{
				setState(47);
				printStatement();
				}
				break;
			case THRUSTON:
			case THRUSTOFF:
			case TURN:
			case FIRE:
			case SKIP_ACTION:
				enterOuterAlt(_localctx, 7);
				{
				setState(48);
				actionStatement();
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

	public static class AssignmentStatementContext extends ParserRuleContext {
		public Token variableName;
		public ExpressionContext value;
		public TerminalNode ASSIGN() { return getToken(AsteroidsProgramParser.ASSIGN, 0); }
		public TerminalNode IDENTIFIER() { return getToken(AsteroidsProgramParser.IDENTIFIER, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignmentStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterAssignmentStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitAssignmentStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitAssignmentStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentStatementContext assignmentStatement() throws RecognitionException {
		AssignmentStatementContext _localctx = new AssignmentStatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_assignmentStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			((AssignmentStatementContext)_localctx).variableName = match(IDENTIFIER);
			setState(52);
			match(ASSIGN);
			setState(53);
			((AssignmentStatementContext)_localctx).value = expression(0);
			setState(54);
			match(T__0);
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

	public static class WhileStatementContext extends ParserRuleContext {
		public ExpressionContext condition;
		public StatementSequenceContext body;
		public TerminalNode WHILE() { return getToken(AsteroidsProgramParser.WHILE, 0); }
		public TerminalNode LEFT_BRACE() { return getToken(AsteroidsProgramParser.LEFT_BRACE, 0); }
		public TerminalNode RIGHT_BRACE() { return getToken(AsteroidsProgramParser.RIGHT_BRACE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementSequenceContext statementSequence() {
			return getRuleContext(StatementSequenceContext.class,0);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitWhileStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(WHILE);
			setState(57);
			((WhileStatementContext)_localctx).condition = expression(0);
			setState(58);
			match(LEFT_BRACE);
			setState(59);
			((WhileStatementContext)_localctx).body = statementSequence();
			setState(60);
			match(RIGHT_BRACE);
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

	public static class BreakStatementContext extends ParserRuleContext {
		public TerminalNode BREAK() { return getToken(AsteroidsProgramParser.BREAK, 0); }
		public BreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitBreakStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitBreakStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_breakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(BREAK);
			setState(63);
			match(T__0);
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

	public static class ReturnStatementContext extends ParserRuleContext {
		public ExpressionContext value;
		public TerminalNode RETURN() { return getToken(AsteroidsProgramParser.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_returnStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(RETURN);
			setState(66);
			((ReturnStatementContext)_localctx).value = expression(0);
			setState(67);
			match(T__0);
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

	public static class IfStatementContext extends ParserRuleContext {
		public ExpressionContext condition;
		public StatementSequenceContext ifbody;
		public StatementSequenceContext elsebody;
		public TerminalNode IF() { return getToken(AsteroidsProgramParser.IF, 0); }
		public List<TerminalNode> LEFT_BRACE() { return getTokens(AsteroidsProgramParser.LEFT_BRACE); }
		public TerminalNode LEFT_BRACE(int i) {
			return getToken(AsteroidsProgramParser.LEFT_BRACE, i);
		}
		public List<TerminalNode> RIGHT_BRACE() { return getTokens(AsteroidsProgramParser.RIGHT_BRACE); }
		public TerminalNode RIGHT_BRACE(int i) {
			return getToken(AsteroidsProgramParser.RIGHT_BRACE, i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<StatementSequenceContext> statementSequence() {
			return getRuleContexts(StatementSequenceContext.class);
		}
		public StatementSequenceContext statementSequence(int i) {
			return getRuleContext(StatementSequenceContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(AsteroidsProgramParser.ELSE, 0); }
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_ifStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(IF);
			setState(70);
			((IfStatementContext)_localctx).condition = expression(0);
			setState(71);
			match(LEFT_BRACE);
			setState(72);
			((IfStatementContext)_localctx).ifbody = statementSequence();
			setState(73);
			match(RIGHT_BRACE);
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(74);
				match(ELSE);
				setState(75);
				match(LEFT_BRACE);
				setState(76);
				((IfStatementContext)_localctx).elsebody = statementSequence();
				setState(77);
				match(RIGHT_BRACE);
				}
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

	public static class PrintStatementContext extends ParserRuleContext {
		public ExpressionContext value;
		public TerminalNode PRINT() { return getToken(AsteroidsProgramParser.PRINT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrintStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterPrintStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitPrintStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitPrintStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintStatementContext printStatement() throws RecognitionException {
		PrintStatementContext _localctx = new PrintStatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_printStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(PRINT);
			setState(82);
			((PrintStatementContext)_localctx).value = expression(0);
			setState(83);
			match(T__0);
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

	public static class StatementSequenceContext extends ParserRuleContext {
		public StatementContext statement;
		public List<StatementContext> stmts = new ArrayList<StatementContext>();
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StatementSequenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementSequence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterStatementSequence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitStatementSequence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitStatementSequence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementSequenceContext statementSequence() throws RecognitionException {
		StatementSequenceContext _localctx = new StatementSequenceContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_statementSequence);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			((StatementSequenceContext)_localctx).statement = statement();
			((StatementSequenceContext)_localctx).stmts.add(((StatementSequenceContext)_localctx).statement);
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << THRUSTON) | (1L << THRUSTOFF) | (1L << TURN) | (1L << FIRE) | (1L << SKIP_ACTION) | (1L << IF) | (1L << WHILE) | (1L << BREAK) | (1L << RETURN) | (1L << PRINT) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(86);
				((StatementSequenceContext)_localctx).statement = statement();
				((StatementSequenceContext)_localctx).stmts.add(((StatementSequenceContext)_localctx).statement);
				}
				}
				setState(91);
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

	public static class ActionStatementContext extends ParserRuleContext {
		public ActionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actionStatement; }
	 
		public ActionStatementContext() { }
		public void copyFrom(ActionStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ThrustOffActionContext extends ActionStatementContext {
		public TerminalNode THRUSTOFF() { return getToken(AsteroidsProgramParser.THRUSTOFF, 0); }
		public ThrustOffActionContext(ActionStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterThrustOffAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitThrustOffAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitThrustOffAction(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SkipActionContext extends ActionStatementContext {
		public TerminalNode SKIP_ACTION() { return getToken(AsteroidsProgramParser.SKIP_ACTION, 0); }
		public SkipActionContext(ActionStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterSkipAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitSkipAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitSkipAction(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TurnActionContext extends ActionStatementContext {
		public ExpressionContext angle;
		public TerminalNode TURN() { return getToken(AsteroidsProgramParser.TURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TurnActionContext(ActionStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterTurnAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitTurnAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitTurnAction(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ThrustOnActionContext extends ActionStatementContext {
		public TerminalNode THRUSTON() { return getToken(AsteroidsProgramParser.THRUSTON, 0); }
		public ThrustOnActionContext(ActionStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterThrustOnAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitThrustOnAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitThrustOnAction(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FireActionContext extends ActionStatementContext {
		public TerminalNode FIRE() { return getToken(AsteroidsProgramParser.FIRE, 0); }
		public FireActionContext(ActionStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterFireAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitFireAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitFireAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionStatementContext actionStatement() throws RecognitionException {
		ActionStatementContext _localctx = new ActionStatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_actionStatement);
		try {
			setState(104);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case THRUSTON:
				_localctx = new ThrustOnActionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(92);
				match(THRUSTON);
				setState(93);
				match(T__0);
				}
				break;
			case THRUSTOFF:
				_localctx = new ThrustOffActionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(94);
				match(THRUSTOFF);
				setState(95);
				match(T__0);
				}
				break;
			case TURN:
				_localctx = new TurnActionContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(96);
				match(TURN);
				setState(97);
				((TurnActionContext)_localctx).angle = expression(0);
				setState(98);
				match(T__0);
				}
				break;
			case FIRE:
				_localctx = new FireActionContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(100);
				match(FIRE);
				setState(101);
				match(T__0);
				}
				break;
			case SKIP_ACTION:
				_localctx = new SkipActionContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(102);
				match(SKIP_ACTION);
				setState(103);
				match(T__0);
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

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ChangeSignExpressionContext extends ExpressionContext {
		public ExpressionContext operand;
		public TerminalNode SUB() { return getToken(AsteroidsProgramParser.SUB, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ChangeSignExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterChangeSignExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitChangeSignExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitChangeSignExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunctCallContext extends ExpressionContext {
		public FunctionCallContext functCall;
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public FunctCallContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterFunctCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitFunctCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitFunctCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumberExpressionContext extends ExpressionContext {
		public Token number;
		public TerminalNode NUMBER() { return getToken(AsteroidsProgramParser.NUMBER, 0); }
		public NumberExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterNumberExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitNumberExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitNumberExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GetYExpressionContext extends ExpressionContext {
		public ExpressionContext expr;
		public TerminalNode GETY() { return getToken(AsteroidsProgramParser.GETY, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public GetYExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterGetYExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitGetYExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitGetYExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotExpressionContext extends ExpressionContext {
		public ExpressionContext expr;
		public TerminalNode NOT() { return getToken(AsteroidsProgramParser.NOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NotExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterNotExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitNotExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitNotExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenExpressionContext extends ExpressionContext {
		public ExpressionContext subExpr;
		public TerminalNode LEFT_PAREN() { return getToken(AsteroidsProgramParser.LEFT_PAREN, 0); }
		public TerminalNode RIGHT_PAREN() { return getToken(AsteroidsProgramParser.RIGHT_PAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParenExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterParenExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitParenExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitParenExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GetRadiusExpressionContext extends ExpressionContext {
		public ExpressionContext expr;
		public TerminalNode GETRADIUS() { return getToken(AsteroidsProgramParser.GETRADIUS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public GetRadiusExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterGetRadiusExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitGetRadiusExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitGetRadiusExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EntityExpressionContext extends ExpressionContext {
		public EntityContext entit;
		public EntityContext entity() {
			return getRuleContext(EntityContext.class,0);
		}
		public EntityExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterEntityExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitEntityExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitEntityExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GetXExpressionContext extends ExpressionContext {
		public ExpressionContext expr;
		public TerminalNode GETX() { return getToken(AsteroidsProgramParser.GETX, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public GetXExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterGetXExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitGetXExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitGetXExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReadVariableExpressionContext extends ExpressionContext {
		public Token variable;
		public TerminalNode IDENTIFIER() { return getToken(AsteroidsProgramParser.IDENTIFIER, 0); }
		public ReadVariableExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterReadVariableExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitReadVariableExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitReadVariableExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GetVXExpressionContext extends ExpressionContext {
		public ExpressionContext expr;
		public TerminalNode GETVX() { return getToken(AsteroidsProgramParser.GETVX, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public GetVXExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterGetVXExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitGetVXExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitGetVXExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReadParameterExpressionContext extends ExpressionContext {
		public Token parameter;
		public TerminalNode PARAM() { return getToken(AsteroidsProgramParser.PARAM, 0); }
		public ReadParameterExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterReadParameterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitReadParameterExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitReadParameterExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GetDirectionExpressionContext extends ExpressionContext {
		public Token dir;
		public TerminalNode GETDIR() { return getToken(AsteroidsProgramParser.GETDIR, 0); }
		public GetDirectionExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterGetDirectionExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitGetDirectionExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitGetDirectionExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddExpressionContext extends ExpressionContext {
		public ExpressionContext leftAdd;
		public ExpressionContext rightAdd;
		public TerminalNode ADD() { return getToken(AsteroidsProgramParser.ADD, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AddExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterAddExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitAddExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitAddExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GetVYExpressionContext extends ExpressionContext {
		public ExpressionContext expr;
		public TerminalNode GETVY() { return getToken(AsteroidsProgramParser.GETVY, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public GetVYExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterGetVYExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitGetVYExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitGetVYExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LessThanExpressionContext extends ExpressionContext {
		public ExpressionContext leftLt;
		public ExpressionContext rightLt;
		public TerminalNode LT() { return getToken(AsteroidsProgramParser.LT, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public LessThanExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterLessThanExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitLessThanExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitLessThanExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SqrtExpressionContext extends ExpressionContext {
		public ExpressionContext expr;
		public TerminalNode SQRT() { return getToken(AsteroidsProgramParser.SQRT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SqrtExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterSqrtExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitSqrtExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitSqrtExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqualExpressionContext extends ExpressionContext {
		public ExpressionContext leftEq;
		public ExpressionContext rightEq;
		public TerminalNode EQ() { return getToken(AsteroidsProgramParser.EQ, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public EqualExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterEqualExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitEqualExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitEqualExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MulExpressionContext extends ExpressionContext {
		public ExpressionContext leftMul;
		public ExpressionContext rightMult;
		public TerminalNode MUL() { return getToken(AsteroidsProgramParser.MUL, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public MulExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterMulExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitMulExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitMulExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				_localctx = new NumberExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(107);
				((NumberExpressionContext)_localctx).number = match(NUMBER);
				}
				break;
			case 2:
				{
				_localctx = new ReadVariableExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(108);
				((ReadVariableExpressionContext)_localctx).variable = match(IDENTIFIER);
				}
				break;
			case 3:
				{
				_localctx = new ReadParameterExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(109);
				((ReadParameterExpressionContext)_localctx).parameter = match(PARAM);
				}
				break;
			case 4:
				{
				_localctx = new EntityExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(110);
				((EntityExpressionContext)_localctx).entit = entity();
				}
				break;
			case 5:
				{
				_localctx = new ParenExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(111);
				match(LEFT_PAREN);
				setState(112);
				((ParenExpressionContext)_localctx).subExpr = expression(0);
				setState(113);
				match(RIGHT_PAREN);
				}
				break;
			case 6:
				{
				_localctx = new ChangeSignExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(115);
				match(SUB);
				setState(116);
				((ChangeSignExpressionContext)_localctx).operand = expression(14);
				}
				break;
			case 7:
				{
				_localctx = new FunctCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(117);
				((FunctCallContext)_localctx).functCall = functionCall();
				}
				break;
			case 8:
				{
				_localctx = new NotExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(118);
				match(NOT);
				setState(119);
				((NotExpressionContext)_localctx).expr = expression(10);
				}
				break;
			case 9:
				{
				_localctx = new SqrtExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(120);
				match(SQRT);
				setState(121);
				((SqrtExpressionContext)_localctx).expr = expression(9);
				}
				break;
			case 10:
				{
				_localctx = new GetXExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(122);
				match(GETX);
				setState(123);
				((GetXExpressionContext)_localctx).expr = expression(8);
				}
				break;
			case 11:
				{
				_localctx = new GetYExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(124);
				match(GETY);
				setState(125);
				((GetYExpressionContext)_localctx).expr = expression(7);
				}
				break;
			case 12:
				{
				_localctx = new GetVXExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(126);
				match(GETVX);
				setState(127);
				((GetVXExpressionContext)_localctx).expr = expression(6);
				}
				break;
			case 13:
				{
				_localctx = new GetVYExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(128);
				match(GETVY);
				setState(129);
				((GetVYExpressionContext)_localctx).expr = expression(5);
				}
				break;
			case 14:
				{
				_localctx = new GetRadiusExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(130);
				match(GETRADIUS);
				setState(131);
				((GetRadiusExpressionContext)_localctx).expr = expression(4);
				}
				break;
			case 15:
				{
				_localctx = new GetDirectionExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(132);
				((GetDirectionExpressionContext)_localctx).dir = match(GETDIR);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(149);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(147);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new AddExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((AddExpressionContext)_localctx).leftAdd = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(135);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(136);
						match(ADD);
						setState(137);
						((AddExpressionContext)_localctx).rightAdd = expression(14);
						}
						break;
					case 2:
						{
						_localctx = new MulExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((MulExpressionContext)_localctx).leftMul = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(138);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(139);
						match(MUL);
						setState(140);
						((MulExpressionContext)_localctx).rightMult = expression(13);
						}
						break;
					case 3:
						{
						_localctx = new EqualExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((EqualExpressionContext)_localctx).leftEq = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(141);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(142);
						match(EQ);
						setState(143);
						((EqualExpressionContext)_localctx).rightEq = expression(3);
						}
						break;
					case 4:
						{
						_localctx = new LessThanExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((LessThanExpressionContext)_localctx).leftLt = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(144);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(145);
						match(LT);
						setState(146);
						((LessThanExpressionContext)_localctx).rightLt = expression(2);
						}
						break;
					}
					} 
				}
				setState(151);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
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

	public static class EntityContext extends ParserRuleContext {
		public TerminalNode NULL() { return getToken(AsteroidsProgramParser.NULL, 0); }
		public TerminalNode SELF() { return getToken(AsteroidsProgramParser.SELF, 0); }
		public TerminalNode SHIP() { return getToken(AsteroidsProgramParser.SHIP, 0); }
		public TerminalNode ASTEROID() { return getToken(AsteroidsProgramParser.ASTEROID, 0); }
		public TerminalNode PLANETOID() { return getToken(AsteroidsProgramParser.PLANETOID, 0); }
		public TerminalNode BULLET() { return getToken(AsteroidsProgramParser.BULLET, 0); }
		public TerminalNode PLANET() { return getToken(AsteroidsProgramParser.PLANET, 0); }
		public TerminalNode ANY() { return getToken(AsteroidsProgramParser.ANY, 0); }
		public EntityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterEntity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitEntity(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitEntity(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EntityContext entity() throws RecognitionException {
		EntityContext _localctx = new EntityContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_entity);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NULL) | (1L << SELF) | (1L << SHIP) | (1L << ASTEROID) | (1L << PLANETOID) | (1L << BULLET) | (1L << PLANET) | (1L << ANY))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class FunctionCallContext extends ParserRuleContext {
		public Token funcName;
		public ExpressionContext expression;
		public List<ExpressionContext> actualArgs = new ArrayList<ExpressionContext>();
		public TerminalNode LEFT_PAREN() { return getToken(AsteroidsProgramParser.LEFT_PAREN, 0); }
		public TerminalNode RIGHT_PAREN() { return getToken(AsteroidsProgramParser.RIGHT_PAREN, 0); }
		public TerminalNode IDENTIFIER() { return getToken(AsteroidsProgramParser.IDENTIFIER, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AsteroidsProgramParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AsteroidsProgramParser.COMMA, i);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AsteroidsProgramListener ) ((AsteroidsProgramListener)listener).exitFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AsteroidsProgramVisitor ) return ((AsteroidsProgramVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			((FunctionCallContext)_localctx).funcName = match(IDENTIFIER);
			setState(155);
			match(LEFT_PAREN);
			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NULL) | (1L << SELF) | (1L << SHIP) | (1L << ASTEROID) | (1L << PLANETOID) | (1L << BULLET) | (1L << PLANET) | (1L << ANY) | (1L << GETRADIUS) | (1L << GETX) | (1L << GETY) | (1L << GETVX) | (1L << GETVY) | (1L << GETDIR) | (1L << SQRT) | (1L << NOT) | (1L << SUB) | (1L << NUMBER) | (1L << IDENTIFIER) | (1L << PARAM) | (1L << LEFT_PAREN))) != 0)) {
				{
				setState(156);
				((FunctionCallContext)_localctx).expression = expression(0);
				((FunctionCallContext)_localctx).actualArgs.add(((FunctionCallContext)_localctx).expression);
				setState(161);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(157);
					match(COMMA);
					setState(158);
					((FunctionCallContext)_localctx).expression = expression(0);
					((FunctionCallContext)_localctx).actualArgs.add(((FunctionCallContext)_localctx).expression);
					}
					}
					setState(163);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(166);
			match(RIGHT_PAREN);
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

	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 13);
		case 1:
			return precpred(_ctx, 12);
		case 2:
			return precpred(_ctx, 2);
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\64\u00ab\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\7\2 \n\2\f\2\16\2#\13\2"+
		"\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\64\n"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b"+
		"\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tR\n\t\3\n\3\n\3\n\3\n"+
		"\3\13\3\13\7\13Z\n\13\f\13\16\13]\13\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\5\fk\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r"+
		"\u0088\n\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u0096\n"+
		"\r\f\r\16\r\u0099\13\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17\7\17\u00a2\n"+
		"\17\f\17\16\17\u00a5\13\17\5\17\u00a7\n\17\3\17\3\17\3\17\2\3\30\20\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\2\3\3\2\4\13\2\u00bd\2!\3\2\2\2\4&"+
		"\3\2\2\2\6\63\3\2\2\2\b\65\3\2\2\2\n:\3\2\2\2\f@\3\2\2\2\16C\3\2\2\2\20"+
		"G\3\2\2\2\22S\3\2\2\2\24W\3\2\2\2\26j\3\2\2\2\30\u0087\3\2\2\2\32\u009a"+
		"\3\2\2\2\34\u009c\3\2\2\2\36 \5\4\3\2\37\36\3\2\2\2 #\3\2\2\2!\37\3\2"+
		"\2\2!\"\3\2\2\2\"$\3\2\2\2#!\3\2\2\2$%\5\24\13\2%\3\3\2\2\2&\'\7\31\2"+
		"\2\'(\7+\2\2()\7\61\2\2)*\5\24\13\2*+\7\62\2\2+\5\3\2\2\2,\64\5\b\5\2"+
		"-\64\5\n\6\2.\64\5\f\7\2/\64\5\16\b\2\60\64\5\20\t\2\61\64\5\22\n\2\62"+
		"\64\5\26\f\2\63,\3\2\2\2\63-\3\2\2\2\63.\3\2\2\2\63/\3\2\2\2\63\60\3\2"+
		"\2\2\63\61\3\2\2\2\63\62\3\2\2\2\64\7\3\2\2\2\65\66\7+\2\2\66\67\7\"\2"+
		"\2\678\5\30\r\289\7\3\2\29\t\3\2\2\2:;\7\35\2\2;<\5\30\r\2<=\7\61\2\2"+
		"=>\5\24\13\2>?\7\62\2\2?\13\3\2\2\2@A\7\36\2\2AB\7\3\2\2B\r\3\2\2\2CD"+
		"\7\37\2\2DE\5\30\r\2EF\7\3\2\2F\17\3\2\2\2GH\7\32\2\2HI\5\30\r\2IJ\7\61"+
		"\2\2JK\5\24\13\2KQ\7\62\2\2LM\7\34\2\2MN\7\61\2\2NO\5\24\13\2OP\7\62\2"+
		"\2PR\3\2\2\2QL\3\2\2\2QR\3\2\2\2R\21\3\2\2\2ST\7 \2\2TU\5\30\r\2UV\7\3"+
		"\2\2V\23\3\2\2\2W[\5\6\4\2XZ\5\6\4\2YX\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\"+
		"\3\2\2\2\\\25\3\2\2\2][\3\2\2\2^_\7\24\2\2_k\7\3\2\2`a\7\25\2\2ak\7\3"+
		"\2\2bc\7\26\2\2cd\5\30\r\2de\7\3\2\2ek\3\2\2\2fg\7\27\2\2gk\7\3\2\2hi"+
		"\7\30\2\2ik\7\3\2\2j^\3\2\2\2j`\3\2\2\2jb\3\2\2\2jf\3\2\2\2jh\3\2\2\2"+
		"k\27\3\2\2\2lm\b\r\1\2m\u0088\7(\2\2n\u0088\7+\2\2o\u0088\7,\2\2p\u0088"+
		"\5\32\16\2qr\7/\2\2rs\5\30\r\2st\7\60\2\2t\u0088\3\2\2\2uv\7%\2\2v\u0088"+
		"\5\30\r\20w\u0088\5\34\17\2xy\7\23\2\2y\u0088\5\30\r\fz{\7\22\2\2{\u0088"+
		"\5\30\r\13|}\7\r\2\2}\u0088\5\30\r\n~\177\7\16\2\2\177\u0088\5\30\r\t"+
		"\u0080\u0081\7\17\2\2\u0081\u0088\5\30\r\b\u0082\u0083\7\20\2\2\u0083"+
		"\u0088\5\30\r\7\u0084\u0085\7\f\2\2\u0085\u0088\5\30\r\6\u0086\u0088\7"+
		"\21\2\2\u0087l\3\2\2\2\u0087n\3\2\2\2\u0087o\3\2\2\2\u0087p\3\2\2\2\u0087"+
		"q\3\2\2\2\u0087u\3\2\2\2\u0087w\3\2\2\2\u0087x\3\2\2\2\u0087z\3\2\2\2"+
		"\u0087|\3\2\2\2\u0087~\3\2\2\2\u0087\u0080\3\2\2\2\u0087\u0082\3\2\2\2"+
		"\u0087\u0084\3\2\2\2\u0087\u0086\3\2\2\2\u0088\u0097\3\2\2\2\u0089\u008a"+
		"\f\17\2\2\u008a\u008b\7$\2\2\u008b\u0096\5\30\r\20\u008c\u008d\f\16\2"+
		"\2\u008d\u008e\7#\2\2\u008e\u0096\5\30\r\17\u008f\u0090\f\4\2\2\u0090"+
		"\u0091\7&\2\2\u0091\u0096\5\30\r\5\u0092\u0093\f\3\2\2\u0093\u0094\7\'"+
		"\2\2\u0094\u0096\5\30\r\4\u0095\u0089\3\2\2\2\u0095\u008c\3\2\2\2\u0095"+
		"\u008f\3\2\2\2\u0095\u0092\3\2\2\2\u0096\u0099\3\2\2\2\u0097\u0095\3\2"+
		"\2\2\u0097\u0098\3\2\2\2\u0098\31\3\2\2\2\u0099\u0097\3\2\2\2\u009a\u009b"+
		"\t\2\2\2\u009b\33\3\2\2\2\u009c\u009d\7+\2\2\u009d\u00a6\7/\2\2\u009e"+
		"\u00a3\5\30\r\2\u009f\u00a0\7-\2\2\u00a0\u00a2\5\30\r\2\u00a1\u009f\3"+
		"\2\2\2\u00a2\u00a5\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4"+
		"\u00a7\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u009e\3\2\2\2\u00a6\u00a7\3\2"+
		"\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\7\60\2\2\u00a9\35\3\2\2\2\f!\63Q"+
		"[j\u0087\u0095\u0097\u00a3\u00a6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
