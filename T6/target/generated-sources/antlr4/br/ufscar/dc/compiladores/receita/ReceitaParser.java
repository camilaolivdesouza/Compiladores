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
		PONTO_VIRGULA=30, ABRE_PAREN=31, FECHA_PAREN=32, WS=33, ERRO_CADEIA=34, 
		CARACTERE_INVALIDO=35;
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
			"PONTO_VIRGULA", "ABRE_PAREN", "FECHA_PAREN", "WS", "ERRO_CADEIA", "CARACTERE_INVALIDO"
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
		public SubstituicaoContext substituicao() {
			return getRuleContext(SubstituicaoContext.class,0);
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
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SUBSTITUIR) {
				{
				setState(47);
				substituicao();
				}
			}

			setState(50);
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
			setState(52);
			match(SUBSTITUIR);
			setState(53);
			restricao();
			setState(54);
			match(POR);
			setState(55);
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
			setState(57);
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
			setState(59);
			match(PASSOS);
			setState(60);
			match(DOIS_PONTOS);
			setState(62); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(61);
				passo();
				}
				}
				setState(64); 
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
			setState(66);
			acao();
			setState(67);
			match(ABRE_PAREN);
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMERO || _la==STRING) {
				{
				setState(68);
				argumentos();
				}
			}

			setState(71);
			match(FECHA_PAREN);
			setState(72);
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
			setState(74);
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
			setState(76);
			argumento();
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIRGULA) {
				{
				{
				setState(77);
				match(VIRGULA);
				setState(78);
				argumento();
				}
				}
				setState(83);
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
			setState(89);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(84);
				match(STRING);
				}
				break;
			case NUMERO:
				enterOuterAlt(_localctx, 2);
				{
				setState(85);
				match(NUMERO);
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << G) | (1L << KG) | (1L << ML) | (1L << L) | (1L << UN) | (1L << C) | (1L << MIN))) != 0)) {
					{
					setState(86);
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
			setState(91);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3%`\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f"+
		"\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3"+
		"\4\6\4*\n\4\r\4\16\4+\3\5\3\5\3\5\3\5\3\5\5\5\63\n\5\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\6\bA\n\b\r\b\16\bB\3\t\3\t\3\t\5\tH\n\t"+
		"\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\13\7\13R\n\13\f\13\16\13U\13\13\3\f\3"+
		"\f\3\f\5\fZ\n\f\5\f\\\n\f\3\r\3\r\3\r\2\2\16\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\2\5\3\2\13\16\3\2\17\24\3\2\25\33\2Z\2\32\3\2\2\2\4 \3\2\2\2\6&\3"+
		"\2\2\2\b-\3\2\2\2\n\66\3\2\2\2\f;\3\2\2\2\16=\3\2\2\2\20D\3\2\2\2\22L"+
		"\3\2\2\2\24N\3\2\2\2\26[\3\2\2\2\30]\3\2\2\2\32\33\5\4\3\2\33\34\5\6\4"+
		"\2\34\35\5\16\b\2\35\36\7\7\2\2\36\37\7\2\2\3\37\3\3\2\2\2 !\7\3\2\2!"+
		"\"\7\35\2\2\"#\7\4\2\2#$\7\36\2\2$%\7\34\2\2%\5\3\2\2\2&\'\7\5\2\2\')"+
		"\7\36\2\2(*\5\b\5\2)(\3\2\2\2*+\3\2\2\2+)\3\2\2\2+,\3\2\2\2,\7\3\2\2\2"+
		"-.\7\34\2\2./\5\30\r\2/\60\7\n\2\2\60\62\7\35\2\2\61\63\5\n\6\2\62\61"+
		"\3\2\2\2\62\63\3\2\2\2\63\64\3\2\2\2\64\65\7 \2\2\65\t\3\2\2\2\66\67\7"+
		"\b\2\2\678\5\f\7\289\7\t\2\29:\7\35\2\2:\13\3\2\2\2;<\t\2\2\2<\r\3\2\2"+
		"\2=>\7\6\2\2>@\7\36\2\2?A\5\20\t\2@?\3\2\2\2AB\3\2\2\2B@\3\2\2\2BC\3\2"+
		"\2\2C\17\3\2\2\2DE\5\22\n\2EG\7!\2\2FH\5\24\13\2GF\3\2\2\2GH\3\2\2\2H"+
		"I\3\2\2\2IJ\7\"\2\2JK\7 \2\2K\21\3\2\2\2LM\t\3\2\2M\23\3\2\2\2NS\5\26"+
		"\f\2OP\7\37\2\2PR\5\26\f\2QO\3\2\2\2RU\3\2\2\2SQ\3\2\2\2ST\3\2\2\2T\25"+
		"\3\2\2\2US\3\2\2\2V\\\7\35\2\2WY\7\34\2\2XZ\5\30\r\2YX\3\2\2\2YZ\3\2\2"+
		"\2Z\\\3\2\2\2[V\3\2\2\2[W\3\2\2\2\\\27\3\2\2\2]^\t\4\2\2^\31\3\2\2\2\t"+
		"+\62BGSY[";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}