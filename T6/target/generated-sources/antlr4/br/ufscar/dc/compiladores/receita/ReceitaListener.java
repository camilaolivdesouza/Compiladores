// Generated from br/ufscar/dc/compiladores/receita/Receita.g4 by ANTLR 4.7.2
package br.ufscar.dc.compiladores.receita;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ReceitaParser}.
 */
public interface ReceitaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ReceitaParser#receita}.
	 * @param ctx the parse tree
	 */
	void enterReceita(ReceitaParser.ReceitaContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReceitaParser#receita}.
	 * @param ctx the parse tree
	 */
	void exitReceita(ReceitaParser.ReceitaContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReceitaParser#cabecalho}.
	 * @param ctx the parse tree
	 */
	void enterCabecalho(ReceitaParser.CabecalhoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReceitaParser#cabecalho}.
	 * @param ctx the parse tree
	 */
	void exitCabecalho(ReceitaParser.CabecalhoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReceitaParser#ingredientes}.
	 * @param ctx the parse tree
	 */
	void enterIngredientes(ReceitaParser.IngredientesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReceitaParser#ingredientes}.
	 * @param ctx the parse tree
	 */
	void exitIngredientes(ReceitaParser.IngredientesContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReceitaParser#ingrediente}.
	 * @param ctx the parse tree
	 */
	void enterIngrediente(ReceitaParser.IngredienteContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReceitaParser#ingrediente}.
	 * @param ctx the parse tree
	 */
	void exitIngrediente(ReceitaParser.IngredienteContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReceitaParser#substituicao}.
	 * @param ctx the parse tree
	 */
	void enterSubstituicao(ReceitaParser.SubstituicaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReceitaParser#substituicao}.
	 * @param ctx the parse tree
	 */
	void exitSubstituicao(ReceitaParser.SubstituicaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReceitaParser#restricao}.
	 * @param ctx the parse tree
	 */
	void enterRestricao(ReceitaParser.RestricaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReceitaParser#restricao}.
	 * @param ctx the parse tree
	 */
	void exitRestricao(ReceitaParser.RestricaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReceitaParser#passos}.
	 * @param ctx the parse tree
	 */
	void enterPassos(ReceitaParser.PassosContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReceitaParser#passos}.
	 * @param ctx the parse tree
	 */
	void exitPassos(ReceitaParser.PassosContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReceitaParser#passo}.
	 * @param ctx the parse tree
	 */
	void enterPasso(ReceitaParser.PassoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReceitaParser#passo}.
	 * @param ctx the parse tree
	 */
	void exitPasso(ReceitaParser.PassoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReceitaParser#acao}.
	 * @param ctx the parse tree
	 */
	void enterAcao(ReceitaParser.AcaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReceitaParser#acao}.
	 * @param ctx the parse tree
	 */
	void exitAcao(ReceitaParser.AcaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReceitaParser#argumentos}.
	 * @param ctx the parse tree
	 */
	void enterArgumentos(ReceitaParser.ArgumentosContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReceitaParser#argumentos}.
	 * @param ctx the parse tree
	 */
	void exitArgumentos(ReceitaParser.ArgumentosContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReceitaParser#argumento}.
	 * @param ctx the parse tree
	 */
	void enterArgumento(ReceitaParser.ArgumentoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReceitaParser#argumento}.
	 * @param ctx the parse tree
	 */
	void exitArgumento(ReceitaParser.ArgumentoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReceitaParser#unidade}.
	 * @param ctx the parse tree
	 */
	void enterUnidade(ReceitaParser.UnidadeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReceitaParser#unidade}.
	 * @param ctx the parse tree
	 */
	void exitUnidade(ReceitaParser.UnidadeContext ctx);
}