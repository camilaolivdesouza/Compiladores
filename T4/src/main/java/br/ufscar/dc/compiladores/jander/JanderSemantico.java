package br.ufscar.dc.compiladores.jander;

import br.ufscar.dc.compiladores.jander.TabelaDeSimbolos.TipoJander;
import org.antlr.v4.runtime.Token;

public class JanderSemantico extends JanderBaseVisitor<Void> {

    // pilha de tabelas de símbolos
    Escopos escopos;

    @Override
    public Void visitPrograma(JanderParser.ProgramaContext ctx) {
        // criação de um escopo global limpo
        escopos = new Escopos();
        return super.visitPrograma(ctx);
    }

    @Override
    public Void visitDeclaracao_local(JanderParser.Declaracao_localContext ctx) {
        // "declare variavel"
        if (ctx.DECLARE() != null && ctx.variavel() != null) {
            JanderParser.VariavelContext varCtx = ctx.variavel();

            String strTipo = varCtx.tipo().getText();

            String tipoBase = strTipo.startsWith("^") ? strTipo.substring(1) : strTipo;

            TipoJander tipoEnum = JanderSemanticoUtils.verificarTipo(tipoBase);

            // Verifica se o tipo é válido
            boolean isRegistro = strTipo.startsWith("registro");
            if (tipoEnum == TipoJander.INVALIDO && !isRegistro) {
                JanderSemanticoUtils.adicionarErroSemantico(varCtx.tipo().start,
                        "tipo " + strTipo + " nao declarado");
            }

            for (var idCtx : varCtx.identificador()) {
                String nomeVar = idCtx.getText();
                TabelaDeSimbolos escopoAtual = escopos.obterEscopoAtual();

                // "O mesmo identificador não pode ser usado novamente no mesmo escopo"
                if (escopoAtual.existe(nomeVar)) {
                    JanderSemanticoUtils.adicionarErroSemantico(idCtx.start,
                            "identificador " + nomeVar + " ja declarado anteriormente");
                } else {
                    escopoAtual.adicionar(nomeVar, isRegistro ? TipoJander.INVALIDO : tipoEnum);
                }
            }
        }

        return super.visitDeclaracao_local(ctx);
    }

    // TRATAMENTO DE ESCOPOS ANINHADOS (Funções e Procedimentos)

    @Override
    public Void visitDeclaracao_global(JanderParser.Declaracao_globalContext ctx) {
        String nomeRotina = ctx.IDENT().getText();
        TabelaDeSimbolos escopoGlobal = escopos.obterEscopoAtual();

        // Verifica se a função/procedimento já foi declarada globalmente
        if (escopoGlobal.existe(nomeRotina)) {
            JanderSemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(),
                    "identificador " + nomeRotina + " ja declarado anteriormente");
        } else {
            escopoGlobal.adicionar(nomeRotina, TipoJander.INVALIDO);
        }

        escopos.criarNovoEscopo();

        if (ctx.parametros() != null) {
            for (var paramCtx : ctx.parametros().parametro()) {
                String strTipo = paramCtx.tipo_estendido().getText();
                String tipoBase = strTipo.startsWith("^") ? strTipo.substring(1) : strTipo;
                TipoJander tipoEnum = JanderSemanticoUtils.verificarTipo(tipoBase);

                for (var idCtx : paramCtx.identificador()) {
                    String nomeParam = idCtx.getText();
                    escopos.obterEscopoAtual().adicionar(nomeParam, tipoEnum);
                }
            }
        }

        super.visitDeclaracao_global(ctx);

        escopos.abandonarEscopo();

        return null;
    }

    // TRATAMENTO DE COMANDOS (Atribuição, Leitura, etc.)

    @Override
    public Void visitCmdAtribuicao(JanderParser.CmdAtribuicaoContext ctx) {
        String nomeVar = ctx.identificador().getText();

        // Busca da variável na pilha de escopos
        boolean declarada = false;
        TipoJander tipoVariavel = TipoJander.INVALIDO;

        for (TabelaDeSimbolos tabela : escopos.percorrerEscoposAninhados()) {
            if (tabela.existe(nomeVar)) {
                declarada = true;
                tipoVariavel = tabela.verificar(nomeVar);
                break;
            }
        }

        // "Identificador não declarado"
        if (!declarada) {
            JanderSemanticoUtils.adicionarErroSemantico(ctx.identificador().start,
                    "identificador " + nomeVar + " nao declarado");
        } else {
            TipoJander tipoExpressao = JanderSemanticoUtils.verificarTipo(escopos, ctx.expressao());

            if (!JanderSemanticoUtils.tiposCompativeis(tipoVariavel, tipoExpressao)) {
                // ponteiro <- endereco (ignoramos tipo aqui se a gramática usar o prefixo ^)
                JanderSemanticoUtils.adicionarErroSemantico(ctx.identificador().start,
                        "atribuicao nao compativel para " + nomeVar);
            }
        }

        return super.visitCmdAtribuicao(ctx);
    }

    @Override
    public Void visitCmdLeia(JanderParser.CmdLeiaContext ctx) {
        // Ao ler uma variável do teclado, ela precisa ter sido declarada.
        for (var idCtx : ctx.identificador()) {
            String nomeVar = idCtx.getText();
            boolean declarada = false;

            for (TabelaDeSimbolos tabela : escopos.percorrerEscoposAninhados()) {
                if (tabela.existe(nomeVar)) {
                    declarada = true;
                    break;
                }
            }

            if (!declarada) {
                JanderSemanticoUtils.adicionarErroSemantico(idCtx.start,
                        "identificador " + nomeVar + " nao declarado");
            }
        }
        return super.visitCmdLeia(ctx);
    }

    @Override
    public Void visitCmdEscreva(JanderParser.CmdEscrevaContext ctx) {
        // Ao escrever algo, precisamos garantir que as expressões e variáveis internas existam
        for (var expCtx : ctx.expressao()) {
            JanderSemanticoUtils.verificarTipo(escopos, expCtx);
        }
        return super.visitCmdEscreva(ctx);
    }

    @Override
    public Void visitCmdEnquanto(JanderParser.CmdEnquantoContext ctx) {
        JanderSemanticoUtils.verificarTipo(escopos, ctx.expressao());
        return super.visitCmdEnquanto(ctx);
    }

}