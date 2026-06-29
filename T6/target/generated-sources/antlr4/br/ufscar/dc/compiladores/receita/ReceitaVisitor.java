// Generated from br/ufscar/dc/compiladores/receita/Receita.g4 by ANTLR 4.7.2
package br.ufscar.dc.compiladores.receita;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ReceitaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ReceitaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ReceitaParser#receita}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReceita(ReceitaParser.ReceitaContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReceitaParser#cabecalho}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCabecalho(ReceitaParser.CabecalhoContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReceitaParser#ingredientes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIngredientes(ReceitaParser.IngredientesContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReceitaParser#ingrediente}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIngrediente(ReceitaParser.IngredienteContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReceitaParser#substituicao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubstituicao(ReceitaParser.SubstituicaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReceitaParser#restricao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRestricao(ReceitaParser.RestricaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReceitaParser#passos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPassos(ReceitaParser.PassosContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReceitaParser#passo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPasso(ReceitaParser.PassoContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReceitaParser#acao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAcao(ReceitaParser.AcaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReceitaParser#argumentos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentos(ReceitaParser.ArgumentosContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReceitaParser#argumento}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumento(ReceitaParser.ArgumentoContext ctx);
	/**
	 * Visit a parse tree produced by {@link ReceitaParser#unidade}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnidade(ReceitaParser.UnidadeContext ctx);
}