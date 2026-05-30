package br.ufscar.dc.compiladores.jander;

import java.util.ArrayList;
import java.util.List;

// Visitor que faz a análise semântica do programa.
public class JanderSemantico extends JanderBaseVisitor<Void> {

    Escopos escopos;
    boolean dentroDeFuncao = false;

    @Override
    public Void visitPrograma(JanderParser.ProgramaContext ctx) {
        // Inicializa a pilha de escopos no início da análise do programa
        escopos = new Escopos();
        return super.visitPrograma(ctx);
    }

    // Busca um identificador em todos os escopos aninhados
    private TabelaDeSimbolos.EntradaTabelaDeSimbolos buscarEmEscopos(String nome) {
        for (TabelaDeSimbolos tab : escopos.percorrerEscoposAninhados()) {
            if (tab.existe(nome)) return tab.verificar(nome);
        }
        return null;
    }

    // Constroi a tabela de campos para um registro, incluindo registros aninhados e ponteiros
    private TabelaDeSimbolos preencherCamposRegistro(JanderParser.RegistroContext ctx) {
        TabelaDeSimbolos tabelaCampos = new TabelaDeSimbolos();
        for (var varCtx : ctx.variavel()) {
            String tipoBruto = varCtx.tipo().getText();
            boolean ehPonteiro = tipoBruto.startsWith("^");
            String tBase = ehPonteiro ? tipoBruto.substring(1) : tipoBruto;

            TabelaDeSimbolos subCampos = null;
            if (varCtx.tipo().registro() != null) {
                subCampos = preencherCamposRegistro(varCtx.tipo().registro());
                tBase = "registro";
            } else {
                TabelaDeSimbolos.EntradaTabelaDeSimbolos defTipo = buscarEmEscopos(tBase);
                if (defTipo != null && defTipo.categoria == TabelaDeSimbolos.Categoria.TIPO) {
                    subCampos = defTipo.campos;
                }
            }

            String tipoFinal = ehPonteiro ? "^" + tBase : tBase;

            for (var idCtx : varCtx.identificador()) {
                String nomeCampo = idCtx.IDENT(0).getText();
                tabelaCampos.adicionar(nomeCampo, TabelaDeSimbolos.Categoria.VARIAVEL, tipoFinal);
                if (subCampos != null) {
                    tabelaCampos.verificar(nomeCampo).campos = subCampos;
                }
            }
        }
        return tabelaCampos;
    }

    @Override
    public Void visitDeclaracao_local(JanderParser.Declaracao_localContext ctx) {
        TabelaDeSimbolos escopoAtual = escopos.obterEscopoAtual();

        // 1. Tratamento para CONSTANTES
        if (ctx.getText().startsWith("constante")) {
            String nomeConstante = ctx.IDENT().getText();
            if (escopoAtual.existe(nomeConstante)) {
                JanderSemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(), "identificador " + nomeConstante + " ja declarado anteriormente");
            } else {
                String tipoConst = ctx.tipo_basico() != null ? ctx.tipo_basico().getText() : "inteiro";
                escopoAtual.adicionar(nomeConstante, TabelaDeSimbolos.Categoria.CONSTANTE, tipoConst);
            }
        }

        // 2. Tratamento para declaração de tipos e registros
        if (ctx.TIPO() != null) {
            String nomeTipo = ctx.IDENT().getText();
            if (escopoAtual.existe(nomeTipo)) {
                JanderSemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(), "identificador " + nomeTipo + " ja declarado anteriormente");
            } else {
                String tipoTexto = ctx.tipo().registro() != null ? "registro" : ctx.tipo().getText();
                escopoAtual.adicionar(nomeTipo, TabelaDeSimbolos.Categoria.TIPO, tipoTexto);
                if (ctx.tipo().registro() != null) {
                    escopoAtual.verificar(nomeTipo).campos = preencherCamposRegistro(ctx.tipo().registro());
                }
            }
        }

        // 3. Tratamento para declaração de variáveis locais
        if (ctx.DECLARE() != null && ctx.variavel() != null) {
            var varCtx = ctx.variavel();
            String tipoBruto = varCtx.tipo().getText();
            boolean ehPonteiro = tipoBruto.startsWith("^");
            String tBase = ehPonteiro ? tipoBruto.substring(1) : tipoBruto;

            TabelaDeSimbolos subCampos = null;
            if (varCtx.tipo().registro() != null) {
                subCampos = preencherCamposRegistro(varCtx.tipo().registro());
                tBase = "registro";
            } else {
                TabelaDeSimbolos.EntradaTabelaDeSimbolos defTipo = buscarEmEscopos(tBase);
                if (defTipo != null && defTipo.categoria == TabelaDeSimbolos.Categoria.TIPO) {
                    subCampos = defTipo.campos;
                } else if (!tBase.equals("inteiro") && !tBase.equals("real") && !tBase.equals("literal") && !tBase.equals("logico")) {
                    JanderSemanticoUtils.adicionarErroSemantico(varCtx.tipo().start, "tipo " + tBase + " nao declarado");
                    tBase = "invalido";
                }
            }

            String tipoFinal = ehPonteiro ? "^" + tBase : tBase;

            for (var idCtx : varCtx.identificador()) {
                String nomeVar = idCtx.IDENT(0).getText();
                if (escopoAtual.existe(nomeVar)) {
                    JanderSemanticoUtils.adicionarErroSemantico(idCtx.start, "identificador " + nomeVar + " ja declarado anteriormente");
                } else {
                    escopoAtual.adicionar(nomeVar, TabelaDeSimbolos.Categoria.VARIAVEL, tipoFinal);
                    if (subCampos != null) {
                        escopoAtual.verificar(nomeVar).campos = subCampos;
                    }
                }
            }
        }

        return super.visitDeclaracao_local(ctx);
    }

    @Override
    public Void visitDeclaracao_global(JanderParser.Declaracao_globalContext ctx) {
        String nomeRotina = ctx.IDENT().getText();
        TabelaDeSimbolos escopoGlobal = escopos.obterEscopoAtual();
        TabelaDeSimbolos.Categoria cat = ctx.FUNCAO() != null ? TabelaDeSimbolos.Categoria.FUNCAO : TabelaDeSimbolos.Categoria.PROCEDIMENTO;

        if (escopoGlobal.existe(nomeRotina)) {
            JanderSemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(), "identificador " + nomeRotina + " ja declarado anteriormente");
        } else {
            String tipoRetorno = (cat == TabelaDeSimbolos.Categoria.FUNCAO) ? ctx.tipo_estendido().getText() : "void";
            escopoGlobal.adicionar(nomeRotina, cat, tipoRetorno);
        }

        escopos.criarNovoEscopo();
        TabelaDeSimbolos.EntradaTabelaDeSimbolos rotinaRef = escopoGlobal.verificar(nomeRotina);

        // Processa parâmetros da rotina, adicionando-os ao escopo local da função/procedimento
        if (ctx.parametros() != null) {
            for (var paramCtx : ctx.parametros().parametro()) {
                String tipoParam = paramCtx.tipo_estendido().getText();
                String tBase = tipoParam.startsWith("^") ? tipoParam.substring(1) : tipoParam;

                TabelaDeSimbolos subCampos = null;
                TabelaDeSimbolos.EntradaTabelaDeSimbolos defTipo = buscarEmEscopos(tBase);
                if (defTipo != null && defTipo.categoria == TabelaDeSimbolos.Categoria.TIPO) {
                    subCampos = defTipo.campos;
                }

                for (var idCtx : paramCtx.identificador()) {
                    String nomeParam = idCtx.IDENT(0).getText();
                    if (escopos.obterEscopoAtual().existe(nomeParam)) {
                        JanderSemanticoUtils.adicionarErroSemantico(idCtx.start, "identificador " + nomeParam + " ja declarado anteriormente");
                    } else {
                        escopos.obterEscopoAtual().adicionar(nomeParam, TabelaDeSimbolos.Categoria.VARIAVEL, tipoParam);
                        if (subCampos != null) {
                            escopos.obterEscopoAtual().verificar(nomeParam).campos = subCampos;
                        }
                        if (rotinaRef != null) {
                            rotinaRef.parametros.add(tipoParam);
                        }
                    }
                }
            }
        }

        boolean antigaDentroDeFuncao = dentroDeFuncao;
        dentroDeFuncao = (cat == TabelaDeSimbolos.Categoria.FUNCAO);

        super.visitDeclaracao_global(ctx);

        dentroDeFuncao = antigaDentroDeFuncao;
        escopos.abandonarEscopo();
        return null;
    }

    @Override
    public Void visitCmdChamada(JanderParser.CmdChamadaContext ctx) {
        String nomeRotina = ctx.IDENT().getText();
        TabelaDeSimbolos.EntradaTabelaDeSimbolos entrada = buscarEmEscopos(nomeRotina);

        if (entrada == null) {
            JanderSemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(), "identificador " + nomeRotina + " nao declarado");
            return super.visitCmdChamada(ctx);
        }

        List<String> tiposArgs = new ArrayList<>();
        if (ctx.expressao() != null) {
            for (var exp : ctx.expressao()) {
                tiposArgs.add(JanderSemanticoUtils.verificarTipo(escopos, exp));
            }
        }

        if (entrada.parametros.size() != tiposArgs.size()) {
            JanderSemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(), "incompatibilidade de parametros na chamada de " + nomeRotina);
        } else {
            for (int i = 0; i < tiposArgs.size(); i++) {
                if (!JanderSemanticoUtils.compativelParametro(entrada.parametros.get(i), tiposArgs.get(i))) {
                    JanderSemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(), "incompatibilidade de parametros na chamada de " + nomeRotina);
                    break;
                }
            }
        }
        return super.visitCmdChamada(ctx);
    }

    @Override
    public Void visitCmdAtribuicao(JanderParser.CmdAtribuicaoContext ctx) {
        String tipoVar = JanderSemanticoUtils.obterTipoIdentificador(escopos, ctx.identificador());

        if (!tipoVar.equals("invalido")) {
            if (ctx.ENDERECO() != null) {
                if (tipoVar.startsWith("^")) {
                    tipoVar = tipoVar.substring(1);
                } else {
                    tipoVar = "invalido";
                }
            }

            String tipoExpressao = JanderSemanticoUtils.verificarTipo(escopos, ctx.expressao());

            if (!JanderSemanticoUtils.compativelAtribuicao(tipoVar, tipoExpressao)) {
                String prefix = ctx.ENDERECO() != null ? "^" : "";
                JanderSemanticoUtils.adicionarErroSemantico(ctx.identificador().start, "atribuicao nao compativel para " + prefix + ctx.identificador().getText());
            }
        }
        return super.visitCmdAtribuicao(ctx);
    }

    // Visita ao CmdSe adicionada para inferir expressões condicionais
    @Override
    public Void visitCmdSe(JanderParser.CmdSeContext ctx) {
        JanderSemanticoUtils.verificarTipo(escopos, ctx.expressao());
        return super.visitCmdSe(ctx);
    }

    @Override
    public Void visitCmdRetorne(JanderParser.CmdRetorneContext ctx) {
        if (!dentroDeFuncao) {
            JanderSemanticoUtils.adicionarErroSemantico(ctx.start, "comando retorne nao permitido nesse escopo");
        }
        return super.visitCmdRetorne(ctx);
    }

    @Override
    public Void visitCmdLeia(JanderParser.CmdLeiaContext ctx) {
        for (var idCtx : ctx.identificador()) {
            JanderSemanticoUtils.obterTipoIdentificador(escopos, idCtx);
        }
        return super.visitCmdLeia(ctx);
    }

    @Override
    public Void visitCmdEscreva(JanderParser.CmdEscrevaContext ctx) {
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