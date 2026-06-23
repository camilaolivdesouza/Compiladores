// Generated from br/ufscar/dc/compiladores/receita/Receita.g4 by ANTLR 4.7.2
package br.ufscar.dc.compiladores.receita;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ReceitaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		RECEITA=1, PORCOES=2, INGREDIENTES=3, PASSOS=4, FIM=5, SUBSTITUIR=6, POR=7, 
		DE=8, VEGANO=9, VEGETARIANO=10, LACTOSE=11, GLUTEN=12, MISTURAR=13, ADICIONAR=14, 
		ASSAR=15, BATER=16, REFRIGERAR=17, COZINHAR=18, G=19, KG=20, ML=21, L=22, 
		UN=23, C=24, MIN=25, NUMERO=26, STRING=27, DOIS_PONTOS=28, VIRGULA=29, 
		PONTO_VIRGULA=30, ABRE_PAREN=31, FECHA_PAREN=32, WS=33;
	public static final int
		RULE_receita = 0, RULE_cabecalho = 1, RULE_ingredientes = 2, RULE_ingrediente = 3, 
		RULE_substituicao = 4, RULE_restricao = 5, RULE_passos = 6, RULE_passo = 7, 
		RULE_acao = 8, RULE_argumentos = 9, RULE_argumento = 10, RULE_unidade = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"receita", "cabecalho", "ingredientes", "ingrediente", "substituicao", 
			"restricao", "passos", "passo", "acao", "argumentos", "argumento", "unidade"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'RECEITA'", "'PORCOES'", "'INGREDIENTES'", "'PASSOS'", "'FIM'", 
			"'SUBSTITUIR'", "'POR'", "'de'", "'vegano'", "'vegetariano'", "'lactose'", 
			"'gluten'", "'MISTURAR'", "'ADICIONAR'", "'ASSAR'", "'BATER'", "'REFRIGERAR'", 
			"'COZINHAR'", "'g'", "'kg'", "'ml'", "'l'", "'un'", "'C'", "'min'", null, 
			null, "':'", "','", "';'", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "RECEITA", "PORCOES", "INGREDIENTES", "PASSOS", "FIM", "SUBSTITUIR", 
			"POR", "DE", "VEGANO", "VEGETARIANO", "LACTOSE", "GLUTEN", "MISTURAR", 
			"ADICIONAR", "ASSAR", "BATER", "REFRIGERAR", "COZINHAR", "G", "KG", "ML", 
			"L", "UN", "C", "MIN", "NUMERO", "STRING", "DOIS_PONTOS", "VIRGULA", 
			"PONTO_VIRGULA", "ABRE_PAREN", "FECHA_PAREN", "WS"
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
	public String getGrammarFileName() { return "Receita.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ReceitaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ReceitaContext extends ParserRuleContext {
		public CabecalhoContext cabecalho() {
			return getRuleContext(CabecalhoContext.class,0);
		}
		public IngredientesContext ingredientes() {
			return getRuleContext(IngredientesContext.class,0);
		}
		public PassosContext passos() {
			return getRuleContext(PassosContext.class,0);
		}
		public TerminalNode FIM() { return getToken(ReceitaParser.FIM, 0); }
		public TerminalNode EOF() { return getToken(ReceitaParser.EOF, 0); }
		public ReceitaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_receita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReceitaListener ) ((ReceitaListener)listener).enterReceita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReceitaListener ) ((ReceitaListener)listener).exitReceita(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReceitaVisitor ) return ((ReceitaVisitor<? extends T>)visitor).visitReceita(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReceitaContext receita() throws RecognitionException {
		ReceitaContext _localctx = new ReceitaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_receita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			cabecalho();
			setState(25);
			ingredientes();
			setState(26);
			passos();
			setState(27);
			match(FIM);
			setState(28);
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

	public static class CabecalhoContext extends ParserRuleContext {
		public TerminalNode RECEITA() { return getToken(ReceitaParser.RECEITA, 0); }
		public TerminalNode STRING() { return getToken(ReceitaParser.STRING, 0); }
		public TerminalNode PORCOES() { return getToken(ReceitaParser.PORCOES, 0); }
		public TerminalNode DOIS_PONTOS() { return getToken(ReceitaParser.DOIS_PONTOS, 0); }
		public TerminalNode NUMERO() { return getToken(ReceitaParser.NUMERO, 0); }
		public CabecalhoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cabecalho; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReceitaListener ) ((ReceitaListener)listener).enterCabecalho(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReceitaListener ) ((ReceitaListener)listener).exitCabecalho(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReceitaVisitor ) return ((ReceitaVisitor<? extends T>)visitor).visitCabecalho(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CabecalhoContext cabecalho() throws RecognitionException {
		CabecalhoContext _localctx = new CabecalhoContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_cabecalho);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(RECEITA);
			setState(31);
			match(STRING);
			setState(32);
			match(PORCOES);
			setState(33);
			match(DOIS_PONTOS);
			setState(34);
			match(NUMERO);
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

	public static class IngredientesContext extends ParserRuleContext {
		public TerminalNode INGREDIENTES() { return getToken(ReceitaParser.INGREDIENTES, 0); }
		public TerminalNode DOIS_PONTOS() { return getToken(ReceitaParser.DOIS_PONTOS, 0); }
		public List<IngredienteContext> ingrediente() {
			return getRuleContexts(IngredienteContext.class);
		}
		public IngredienteContext ingrediente(int i) {
			return getRuleContext(IngredienteContext.class,i);
		}
		public IngredientesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ingredientes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReceitaListener ) ((ReceitaListener)listener).enterIngredientes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReceitaListener ) ((ReceitaListener)listener).exitIngredientes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReceitaVisitor ) return ((ReceitaVisitor<? extends T>)visitor).visitIngredientes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IngredientesContext ingredientes() throws RecognitionException {
		IngredientesContext _localctx = new IngredientesContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_ingredientes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(INGREDIENTES);
			setState(37);
			match(DOIS_PONTOS);
			setState(39); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(38);
				ingrediente();
				}
				}
				setState(41); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NUMERO );
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

	public static class IngredienteContext extends ParserRuleContext {
		public TerminalNode NUMERO() { return getToken(ReceitaParser.NUMERO, 0); }
		public UnidadeContext unidade() {
			return getRuleContext(UnidadeContext.class,0);
		}
		public TerminalNode DE() { return getToken(ReceitaParser.DE, 0); }
		public TerminalNode STRING() { return getToken(ReceitaParser.STRING, 0); }
		public TerminalNode PONTO_VIRGULA() { return getToken(ReceitaParser.PONTO_VIRGULA, 0); }
		public List<SubstituicaoContext> substituicao() {
			return getRuleContexts(SubstituicaoContext.class);
		}
		public SubstituicaoContext substituicao(int i) {
			return getRuleContext(SubstituicaoContext.class,i);
		}
		public IngredienteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ingrediente; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReceitaListener ) ((ReceitaListener)listener).enterIngrediente(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReceitaListener ) ((ReceitaListener)listener).exitIngrediente(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReceitaVisitor ) return ((ReceitaVisitor<? extends T>)visitor).visitIngrediente(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IngredienteContext ingrediente() throws RecognitionException {
		IngredienteContext _localctx = new IngredienteContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_ingrediente);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			match(NUMERO);
			setState(44);
			unidade();
			setState(45);
			match(DE);
			setState(46);
			match(STRING);
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SUBSTITUIR) {
				{
				{
				setState(47);
				substituicao();
				}
				}
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(53);
			match(PONTO_VIRGULA);
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

	public static class SubstituicaoContext extends ParserRuleContext {
		public TerminalNode SUBSTITUIR() { return getToken(ReceitaParser.SUBSTITUIR, 0); }
		public RestricaoContext restricao() {
			return getRuleContext(RestricaoContext.class,0);
		}
		public TerminalNode POR() { return getToken(ReceitaParser.POR, 0); }
		public TerminalNode STRING() { return getToken(ReceitaParser.STRING, 0); }
		public TerminalNode PONTO_VIRGULA() { return getToken(ReceitaParser.PONTO_VIRGULA, 0); }
		public SubstituicaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_substituicao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReceitaListener ) ((ReceitaListener)listener).enterSubstituicao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReceitaListener ) ((ReceitaListener)listener).exitSubstituicao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReceitaVisitor ) return ((ReceitaVisitor<? extends T>)visitor).visitSubstituicao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubstituicaoContext substituicao() throws RecognitionException {
		SubstituicaoContext _localctx = new SubstituicaoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_substituicao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(SUBSTITUIR);
			setState(56);
			restricao();
			setState(57);
			match(POR);
			setState(58);
			match(STRING);
			setState(59);
			match(PONTO_VIRGULA);
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

	public static class RestricaoContext extends ParserRuleContext {
		public TerminalNode VEGANO() { return getToken(ReceitaParser.VEGANO, 0); }
		public TerminalNode VEGETARIANO() { return getToken(ReceitaParser.VEGETARIANO, 0); }
		public TerminalNode LACTOSE() { return getToken(ReceitaParser.LACTOSE, 0); }
		public TerminalNode GLUTEN() { return getToken(ReceitaParser.GLUTEN, 0); }
		public RestricaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_restricao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReceitaListener ) ((ReceitaListener)listener).enterRestricao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReceitaListener ) ((ReceitaListener)listener).exitRestricao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReceitaVisitor ) return ((ReceitaVisitor<? extends T>)visitor).visitRestricao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RestricaoContext restricao() throws RecognitionException {
		RestricaoContext _localctx = new RestricaoContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_restricao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VEGANO) | (1L << VEGETARIANO) | (1L << LACTOSE) | (1L << GLUTEN))) != 0)) ) {
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

	public static class PassosContext extends ParserRuleContext {
		public TerminalNode PASSOS() { return getToken(ReceitaParser.PASSOS, 0); }
		public TerminalNode DOIS_PONTOS() { return getToken(ReceitaParser.DOIS_PONTOS, 0); }
		public List<PassoContext> passo() {
			return getRuleContexts(PassoContext.class);
		}
		public PassoContext passo(int i) {
			return getRuleContext(PassoContext.class,i);
		}
		public PassosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_passos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReceitaListener ) ((ReceitaListener)listener).enterPassos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReceitaListener ) ((ReceitaListener)listener).exitPassos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReceitaVisitor ) return ((ReceitaVisitor<? extends T>)visitor).visitPassos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PassosContext passos() throws RecognitionException {
		PassosContext _localctx = new PassosContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_passos);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(PASSOS);
			setState(64);
			match(DOIS_PONTOS);
			setState(66); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(65);
				passo();
				}
				}
				setState(68); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MISTURAR) | (1L << ADICIONAR) | (1L << ASSAR) | (1L << BATER) | (1L << REFRIGERAR) | (1L << COZINHAR))) != 0) );
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

	public static class PassoContext extends ParserRuleContext {
		public AcaoContext acao() {
			return getRuleContext(AcaoContext.class,0);
		}
		public TerminalNode ABRE_PAREN() { return getToken(ReceitaParser.ABRE_PAREN, 0); }
		public TerminalNode FECHA_PAREN() { return getToken(ReceitaParser.FECHA_PAREN, 0); }
		public TerminalNode PONTO_VIRGULA() { return getToken(ReceitaParser.PONTO_VIRGULA, 0); }
		public ArgumentosContext argumentos() {
			return getRuleContext(ArgumentosContext.class,0);
		}
		public PassoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_passo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReceitaListener ) ((ReceitaListener)listener).enterPasso(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReceitaListener ) ((ReceitaListener)listener).exitPasso(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReceitaVisitor ) return ((ReceitaVisitor<? extends T>)visitor).visitPasso(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PassoContext passo() throws RecognitionException {
		PassoContext _localctx = new PassoContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_passo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			acao();
			setState(71);
			match(ABRE_PAREN);
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMERO || _la==STRING) {
				{
				setState(72);
				argumentos();
				}
			}

			setState(75);
			match(FECHA_PAREN);
			setState(76);
			match(PONTO_VIRGULA);
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

	public static class AcaoContext extends ParserRuleContext {
		public TerminalNode MISTURAR() { return getToken(ReceitaParser.MISTURAR, 0); }
		public TerminalNode ADICIONAR() { return getToken(ReceitaParser.ADICIONAR, 0); }
		public TerminalNode ASSAR() { return getToken(ReceitaParser.ASSAR, 0); }
		public TerminalNode BATER() { return getToken(ReceitaParser.BATER, 0); }
		public TerminalNode REFRIGERAR() { return getToken(ReceitaParser.REFRIGERAR, 0); }
		public TerminalNode COZINHAR() { return getToken(ReceitaParser.COZINHAR, 0); }
		public AcaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_acao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReceitaListener ) ((ReceitaListener)listener).enterAcao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReceitaListener ) ((ReceitaListener)listener).exitAcao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReceitaVisitor ) return ((ReceitaVisitor<? extends T>)visitor).visitAcao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AcaoContext acao() throws RecognitionException {
		AcaoContext _localctx = new AcaoContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_acao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MISTURAR) | (1L << ADICIONAR) | (1L << ASSAR) | (1L << BATER) | (1L << REFRIGERAR) | (1L << COZINHAR))) != 0)) ) {
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

	public static class ArgumentosContext extends ParserRuleContext {
		public List<ArgumentoContext> argumento() {
			return getRuleContexts(ArgumentoContext.class);
		}
		public ArgumentoContext argumento(int i) {
			return getRuleContext(ArgumentoContext.class,i);
		}
		public List<TerminalNode> VIRGULA() { return getTokens(ReceitaParser.VIRGULA); }
		public TerminalNode VIRGULA(int i) {
			return getToken(ReceitaParser.VIRGULA, i);
		}
		public ArgumentosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReceitaListener ) ((ReceitaListener)listener).enterArgumentos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReceitaListener ) ((ReceitaListener)listener).exitArgumentos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReceitaVisitor ) return ((ReceitaVisitor<? extends T>)visitor).visitArgumentos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentosContext argumentos() throws RecognitionException {
		ArgumentosContext _localctx = new ArgumentosContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_argumentos);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			argumento();
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIRGULA) {
				{
				{
				setState(81);
				match(VIRGULA);
				setState(82);
				argumento();
				}
				}
				setState(87);
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

	public static class ArgumentoContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(ReceitaParser.STRING, 0); }
		public TerminalNode NUMERO() { return getToken(ReceitaParser.NUMERO, 0); }
		public UnidadeContext unidade() {
			return getRuleContext(UnidadeContext.class,0);
		}
		public ArgumentoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumento; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReceitaListener ) ((ReceitaListener)listener).enterArgumento(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReceitaListener ) ((ReceitaListener)listener).exitArgumento(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReceitaVisitor ) return ((ReceitaVisitor<? extends T>)visitor).visitArgumento(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentoContext argumento() throws RecognitionException {
		ArgumentoContext _localctx = new ArgumentoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_argumento);
		int _la;
		try {
			setState(93);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(88);
				match(STRING);
				}
				break;
			case NUMERO:
				enterOuterAlt(_localctx, 2);
				{
				setState(89);
				match(NUMERO);
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << G) | (1L << KG) | (1L << ML) | (1L << L) | (1L << UN) | (1L << C) | (1L << MIN))) != 0)) {
					{
					setState(90);
					unidade();
					}
				}

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

	public static class UnidadeContext extends ParserRuleContext {
		public TerminalNode G() { return getToken(ReceitaParser.G, 0); }
		public TerminalNode KG() { return getToken(ReceitaParser.KG, 0); }
		public TerminalNode ML() { return getToken(ReceitaParser.ML, 0); }
		public TerminalNode L() { return getToken(ReceitaParser.L, 0); }
		public TerminalNode UN() { return getToken(ReceitaParser.UN, 0); }
		public TerminalNode C() { return getToken(ReceitaParser.C, 0); }
		public TerminalNode MIN() { return getToken(ReceitaParser.MIN, 0); }
		public UnidadeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unidade; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReceitaListener ) ((ReceitaListener)listener).enterUnidade(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReceitaListener ) ((ReceitaListener)listener).exitUnidade(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReceitaVisitor ) return ((ReceitaVisitor<? extends T>)visitor).visitUnidade(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnidadeContext unidade() throws RecognitionException {
		UnidadeContext _localctx = new UnidadeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_unidade);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << G) | (1L << KG) | (1L << ML) | (1L << L) | (1L << UN) | (1L << C) | (1L << MIN))) != 0)) ) {
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3#d\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f"+
		"\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3"+
		"\4\6\4*\n\4\r\4\16\4+\3\5\3\5\3\5\3\5\3\5\7\5\63\n\5\f\5\16\5\66\13\5"+
		"\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\6\bE\n\b\r\b\16\b"+
		"F\3\t\3\t\3\t\5\tL\n\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\13\7\13V\n\13\f"+
		"\13\16\13Y\13\13\3\f\3\f\3\f\5\f^\n\f\5\f`\n\f\3\r\3\r\3\r\2\2\16\2\4"+
		"\6\b\n\f\16\20\22\24\26\30\2\5\3\2\13\16\3\2\17\24\3\2\25\33\2^\2\32\3"+
		"\2\2\2\4 \3\2\2\2\6&\3\2\2\2\b-\3\2\2\2\n9\3\2\2\2\f?\3\2\2\2\16A\3\2"+
		"\2\2\20H\3\2\2\2\22P\3\2\2\2\24R\3\2\2\2\26_\3\2\2\2\30a\3\2\2\2\32\33"+
		"\5\4\3\2\33\34\5\6\4\2\34\35\5\16\b\2\35\36\7\7\2\2\36\37\7\2\2\3\37\3"+
		"\3\2\2\2 !\7\3\2\2!\"\7\35\2\2\"#\7\4\2\2#$\7\36\2\2$%\7\34\2\2%\5\3\2"+
		"\2\2&\'\7\5\2\2\')\7\36\2\2(*\5\b\5\2)(\3\2\2\2*+\3\2\2\2+)\3\2\2\2+,"+
		"\3\2\2\2,\7\3\2\2\2-.\7\34\2\2./\5\30\r\2/\60\7\n\2\2\60\64\7\35\2\2\61"+
		"\63\5\n\6\2\62\61\3\2\2\2\63\66\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65"+
		"\67\3\2\2\2\66\64\3\2\2\2\678\7 \2\28\t\3\2\2\29:\7\b\2\2:;\5\f\7\2;<"+
		"\7\t\2\2<=\7\35\2\2=>\7 \2\2>\13\3\2\2\2?@\t\2\2\2@\r\3\2\2\2AB\7\6\2"+
		"\2BD\7\36\2\2CE\5\20\t\2DC\3\2\2\2EF\3\2\2\2FD\3\2\2\2FG\3\2\2\2G\17\3"+
		"\2\2\2HI\5\22\n\2IK\7!\2\2JL\5\24\13\2KJ\3\2\2\2KL\3\2\2\2LM\3\2\2\2M"+
		"N\7\"\2\2NO\7 \2\2O\21\3\2\2\2PQ\t\3\2\2Q\23\3\2\2\2RW\5\26\f\2ST\7\37"+
		"\2\2TV\5\26\f\2US\3\2\2\2VY\3\2\2\2WU\3\2\2\2WX\3\2\2\2X\25\3\2\2\2YW"+
		"\3\2\2\2Z`\7\35\2\2[]\7\34\2\2\\^\5\30\r\2]\\\3\2\2\2]^\3\2\2\2^`\3\2"+
		"\2\2_Z\3\2\2\2_[\3\2\2\2`\27\3\2\2\2ab\t\4\2\2b\31\3\2\2\2\t+\64FKW]_";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}