// Generated from br/ufscar/dc/compiladores/receita/Receita.g4 by ANTLR 4.7.2
package br.ufscar.dc.compiladores.receita;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ReceitaLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"RECEITA", "PORCOES", "INGREDIENTES", "PASSOS", "FIM", "SUBSTITUIR", 
			"POR", "DE", "VEGANO", "VEGETARIANO", "LACTOSE", "GLUTEN", "MISTURAR", 
			"ADICIONAR", "ASSAR", "BATER", "REFRIGERAR", "COZINHAR", "G", "KG", "ML", 
			"L", "UN", "C", "MIN", "NUMERO", "STRING", "DOIS_PONTOS", "VIRGULA", 
			"PONTO_VIRGULA", "ABRE_PAREN", "FECHA_PAREN", "WS"
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


	public ReceitaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Receita.g4"; }

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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2#\u0106\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27"+
		"\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3\32\3\33\6\33\u00e9\n\33\r\33"+
		"\16\33\u00ea\3\34\3\34\7\34\u00ef\n\34\f\34\16\34\u00f2\13\34\3\34\3\34"+
		"\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\6\"\u0101\n\"\r\"\16\""+
		"\u0102\3\"\3\"\2\2#\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r"+
		"\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37= ?!A\"C#\3\2\5\3\2\62;\5\2\f\f\17\17$$\5\2\13\f\17"+
		"\17\"\"\2\u0108\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2"+
		"9\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\3E\3"+
		"\2\2\2\5M\3\2\2\2\7U\3\2\2\2\tb\3\2\2\2\13i\3\2\2\2\rm\3\2\2\2\17x\3\2"+
		"\2\2\21|\3\2\2\2\23\177\3\2\2\2\25\u0086\3\2\2\2\27\u0092\3\2\2\2\31\u009a"+
		"\3\2\2\2\33\u00a1\3\2\2\2\35\u00aa\3\2\2\2\37\u00b4\3\2\2\2!\u00ba\3\2"+
		"\2\2#\u00c0\3\2\2\2%\u00cb\3\2\2\2\'\u00d4\3\2\2\2)\u00d6\3\2\2\2+\u00d9"+
		"\3\2\2\2-\u00dc\3\2\2\2/\u00de\3\2\2\2\61\u00e1\3\2\2\2\63\u00e3\3\2\2"+
		"\2\65\u00e8\3\2\2\2\67\u00ec\3\2\2\29\u00f5\3\2\2\2;\u00f7\3\2\2\2=\u00f9"+
		"\3\2\2\2?\u00fb\3\2\2\2A\u00fd\3\2\2\2C\u0100\3\2\2\2EF\7T\2\2FG\7G\2"+
		"\2GH\7E\2\2HI\7G\2\2IJ\7K\2\2JK\7V\2\2KL\7C\2\2L\4\3\2\2\2MN\7R\2\2NO"+
		"\7Q\2\2OP\7T\2\2PQ\7E\2\2QR\7Q\2\2RS\7G\2\2ST\7U\2\2T\6\3\2\2\2UV\7K\2"+
		"\2VW\7P\2\2WX\7I\2\2XY\7T\2\2YZ\7G\2\2Z[\7F\2\2[\\\7K\2\2\\]\7G\2\2]^"+
		"\7P\2\2^_\7V\2\2_`\7G\2\2`a\7U\2\2a\b\3\2\2\2bc\7R\2\2cd\7C\2\2de\7U\2"+
		"\2ef\7U\2\2fg\7Q\2\2gh\7U\2\2h\n\3\2\2\2ij\7H\2\2jk\7K\2\2kl\7O\2\2l\f"+
		"\3\2\2\2mn\7U\2\2no\7W\2\2op\7D\2\2pq\7U\2\2qr\7V\2\2rs\7K\2\2st\7V\2"+
		"\2tu\7W\2\2uv\7K\2\2vw\7T\2\2w\16\3\2\2\2xy\7R\2\2yz\7Q\2\2z{\7T\2\2{"+
		"\20\3\2\2\2|}\7f\2\2}~\7g\2\2~\22\3\2\2\2\177\u0080\7x\2\2\u0080\u0081"+
		"\7g\2\2\u0081\u0082\7i\2\2\u0082\u0083\7c\2\2\u0083\u0084\7p\2\2\u0084"+
		"\u0085\7q\2\2\u0085\24\3\2\2\2\u0086\u0087\7x\2\2\u0087\u0088\7g\2\2\u0088"+
		"\u0089\7i\2\2\u0089\u008a\7g\2\2\u008a\u008b\7v\2\2\u008b\u008c\7c\2\2"+
		"\u008c\u008d\7t\2\2\u008d\u008e\7k\2\2\u008e\u008f\7c\2\2\u008f\u0090"+
		"\7p\2\2\u0090\u0091\7q\2\2\u0091\26\3\2\2\2\u0092\u0093\7n\2\2\u0093\u0094"+
		"\7c\2\2\u0094\u0095\7e\2\2\u0095\u0096\7v\2\2\u0096\u0097\7q\2\2\u0097"+
		"\u0098\7u\2\2\u0098\u0099\7g\2\2\u0099\30\3\2\2\2\u009a\u009b\7i\2\2\u009b"+
		"\u009c\7n\2\2\u009c\u009d\7w\2\2\u009d\u009e\7v\2\2\u009e\u009f\7g\2\2"+
		"\u009f\u00a0\7p\2\2\u00a0\32\3\2\2\2\u00a1\u00a2\7O\2\2\u00a2\u00a3\7"+
		"K\2\2\u00a3\u00a4\7U\2\2\u00a4\u00a5\7V\2\2\u00a5\u00a6\7W\2\2\u00a6\u00a7"+
		"\7T\2\2\u00a7\u00a8\7C\2\2\u00a8\u00a9\7T\2\2\u00a9\34\3\2\2\2\u00aa\u00ab"+
		"\7C\2\2\u00ab\u00ac\7F\2\2\u00ac\u00ad\7K\2\2\u00ad\u00ae\7E\2\2\u00ae"+
		"\u00af\7K\2\2\u00af\u00b0\7Q\2\2\u00b0\u00b1\7P\2\2\u00b1\u00b2\7C\2\2"+
		"\u00b2\u00b3\7T\2\2\u00b3\36\3\2\2\2\u00b4\u00b5\7C\2\2\u00b5\u00b6\7"+
		"U\2\2\u00b6\u00b7\7U\2\2\u00b7\u00b8\7C\2\2\u00b8\u00b9\7T\2\2\u00b9 "+
		"\3\2\2\2\u00ba\u00bb\7D\2\2\u00bb\u00bc\7C\2\2\u00bc\u00bd\7V\2\2\u00bd"+
		"\u00be\7G\2\2\u00be\u00bf\7T\2\2\u00bf\"\3\2\2\2\u00c0\u00c1\7T\2\2\u00c1"+
		"\u00c2\7G\2\2\u00c2\u00c3\7H\2\2\u00c3\u00c4\7T\2\2\u00c4\u00c5\7K\2\2"+
		"\u00c5\u00c6\7I\2\2\u00c6\u00c7\7G\2\2\u00c7\u00c8\7T\2\2\u00c8\u00c9"+
		"\7C\2\2\u00c9\u00ca\7T\2\2\u00ca$\3\2\2\2\u00cb\u00cc\7E\2\2\u00cc\u00cd"+
		"\7Q\2\2\u00cd\u00ce\7\\\2\2\u00ce\u00cf\7K\2\2\u00cf\u00d0\7P\2\2\u00d0"+
		"\u00d1\7J\2\2\u00d1\u00d2\7C\2\2\u00d2\u00d3\7T\2\2\u00d3&\3\2\2\2\u00d4"+
		"\u00d5\7i\2\2\u00d5(\3\2\2\2\u00d6\u00d7\7m\2\2\u00d7\u00d8\7i\2\2\u00d8"+
		"*\3\2\2\2\u00d9\u00da\7o\2\2\u00da\u00db\7n\2\2\u00db,\3\2\2\2\u00dc\u00dd"+
		"\7n\2\2\u00dd.\3\2\2\2\u00de\u00df\7w\2\2\u00df\u00e0\7p\2\2\u00e0\60"+
		"\3\2\2\2\u00e1\u00e2\7E\2\2\u00e2\62\3\2\2\2\u00e3\u00e4\7o\2\2\u00e4"+
		"\u00e5\7k\2\2\u00e5\u00e6\7p\2\2\u00e6\64\3\2\2\2\u00e7\u00e9\t\2\2\2"+
		"\u00e8\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb"+
		"\3\2\2\2\u00eb\66\3\2\2\2\u00ec\u00f0\7$\2\2\u00ed\u00ef\n\3\2\2\u00ee"+
		"\u00ed\3\2\2\2\u00ef\u00f2\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00f1\3\2"+
		"\2\2\u00f1\u00f3\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f3\u00f4\7$\2\2\u00f4"+
		"8\3\2\2\2\u00f5\u00f6\7<\2\2\u00f6:\3\2\2\2\u00f7\u00f8\7.\2\2\u00f8<"+
		"\3\2\2\2\u00f9\u00fa\7=\2\2\u00fa>\3\2\2\2\u00fb\u00fc\7*\2\2\u00fc@\3"+
		"\2\2\2\u00fd\u00fe\7+\2\2\u00feB\3\2\2\2\u00ff\u0101\t\4\2\2\u0100\u00ff"+
		"\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103"+
		"\u0104\3\2\2\2\u0104\u0105\b\"\2\2\u0105D\3\2\2\2\6\2\u00ea\u00f0\u0102"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}