package br.ufscar.dc.compiladores.jander;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.Token;

public class JanderSemanticoUtils {

    public static List<String> errosSemanticos = new ArrayList<>();

    public static void adicionarErroSemantico(Token t, String mensagem) {
        int linha = t.getLine();
        errosSemanticos.add(String.format("Linha %d: %s", linha, mensagem));
    }

    public static boolean compativelAtribuicao(String tipoVar, String tipoExp) {
        if (tipoVar.equals(tipoExp)) return true;
        if (tipoVar.equals("invalido") || tipoExp.equals("invalido")) return false;

        if ((tipoVar.equals("real") || tipoVar.equals("inteiro")) &&
                (tipoExp.equals("real") || tipoExp.equals("inteiro"))) {
            return true;
        }
        if (tipoVar.startsWith("^") && tipoExp.startsWith("&")) {
            return tipoVar.substring(1).equals(tipoExp.substring(1));
        }
        return false;
    }

    public static boolean compativelParametro(String tipoParam, String tipoArg) {
        if (tipoParam.equals(tipoArg)) return true;
        if (tipoParam.equals("invalido") || tipoArg.equals("invalido")) return false;

        if (tipoParam.startsWith("^") && tipoArg.startsWith("&")) {
            return tipoParam.substring(1).equals(tipoArg.substring(1));
        }
        return false;
    }

    public static String obterTipoIdentificador(Escopos escopos, JanderParser.IdentificadorContext ctx) {
        String nomeBase = ctx.IDENT(0).getText();
        TabelaDeSimbolos.EntradaTabelaDeSimbolos entrada = null;

        for (TabelaDeSimbolos tab : escopos.percorrerEscoposAninhados()) {
            if (tab.existe(nomeBase)) {
                entrada = tab.verificar(nomeBase);
                break;
            }
        }

        if (entrada == null) {
            adicionarErroSemantico(ctx.start, "identificador " + ctx.getText() + " nao declarado");
            return "invalido";
        }

        TabelaDeSimbolos escopoCampos = entrada.campos;
        String tipoAtual = entrada.tipo;

        for (int i = 1; i < ctx.IDENT().size(); i++) {
            String nomeCampo = ctx.IDENT(i).getText();

            if (escopoCampos == null) {
                String tipoBusca = tipoAtual.startsWith("^") ? tipoAtual.substring(1) : tipoAtual;
                TabelaDeSimbolos.EntradaTabelaDeSimbolos defTipo = null;
                for (TabelaDeSimbolos tab : escopos.percorrerEscoposAninhados()) {
                    if (tab.existe(tipoBusca)) {
                        defTipo = tab.verificar(tipoBusca);
                        break;
                    }
                }
                if (defTipo != null) {
                    escopoCampos = defTipo.campos;
                }
            }

            if (escopoCampos == null || !escopoCampos.existe(nomeCampo)) {
                adicionarErroSemantico(ctx.start, "identificador " + ctx.getText() + " nao declarado");
                return "invalido";
            }

            TabelaDeSimbolos.EntradaTabelaDeSimbolos campo = escopoCampos.verificar(nomeCampo);
            tipoAtual = campo.tipo;
            escopoCampos = campo.campos;
        }
        return tipoAtual;
    }

    public static String verificarTipo(Escopos escopos, JanderParser.ExpressaoContext ctx) {
        String ret = null;
        for (var tl : ctx.termo_logico()) {
            String aux = verificarTipo(escopos, tl);
            if (ret == null) ret = aux;
            else if (!ret.equals(aux) && !aux.equals("invalido")) ret = "invalido";
        }
        if (ctx.OP_OU() != null && !ctx.OP_OU().isEmpty()) return "logico";
        return ret != null ? ret : "invalido";
    }

    public static String verificarTipo(Escopos escopos, JanderParser.Termo_logicoContext ctx) {
        String ret = null;
        for (var fl : ctx.fator_logico()) {
            String aux = verificarTipo(escopos, fl);
            if (ret == null) ret = aux;
            else if (!ret.equals(aux) && !aux.equals("invalido")) ret = "invalido";
        }
        if (ctx.OP_E() != null && !ctx.OP_E().isEmpty()) return "logico";
        return ret != null ? ret : "invalido";
    }

    public static String verificarTipo(Escopos escopos, JanderParser.Fator_logicoContext ctx) {
        String tipo = verificarTipo(escopos, ctx.parcela_logica());
        if (ctx.OP_NAO() != null) return "logico";
        return tipo;
    }

    public static String verificarTipo(Escopos escopos, JanderParser.Parcela_logicaContext ctx) {
        if (ctx.VERDADEIRO() != null || ctx.FALSO() != null) return "logico";
        return verificarTipo(escopos, ctx.exp_relacional());
    }

    public static String verificarTipo(Escopos escopos, JanderParser.Exp_relacionalContext ctx) {
        String ret = null;
        for (var ea : ctx.exp_aritmetica()) {
            String aux = verificarTipo(escopos, ea);
            if (ret == null) ret = aux;
            else if (!ret.equals(aux) && !aux.equals("invalido")) {
                boolean numCompativel = (ret.equals("inteiro") || ret.equals("real")) &&
                        (aux.equals("inteiro") || aux.equals("real"));
                if (!numCompativel) ret = "invalido";
            }
        }
        if (ctx.op_relacional() != null) return "logico";
        return ret != null ? ret : "invalido";
    }

    public static String verificarTipo(Escopos escopos, JanderParser.Exp_aritmeticaContext ctx) {
        String ret = null;
        for (var t : ctx.termo()) {
            String aux = verificarTipo(escopos, t);
            if (ret == null) ret = aux;
            else if (!ret.equals(aux) && !aux.equals("invalido") && !ret.equals("invalido")) {
                if ((ret.equals("inteiro") && aux.equals("real")) || (ret.equals("real") && aux.equals("inteiro"))) {
                    ret = "real";
                } else {
                    ret = "invalido";
                }
            }
        }
        return ret != null ? ret : "invalido";
    }

    public static String verificarTipo(Escopos escopos, JanderParser.TermoContext ctx) {
        String ret = null;
        for (var f : ctx.fator()) {
            String aux = verificarTipo(escopos, f);
            if (ret == null) ret = aux;
            else if (!ret.equals(aux) && !aux.equals("invalido") && !ret.equals("invalido")) {
                if ((ret.equals("inteiro") && aux.equals("real")) || (ret.equals("real") && aux.equals("inteiro"))) {
                    ret = "real";
                } else {
                    ret = "invalido";
                }
            }
        }
        return ret != null ? ret : "invalido";
    }

    public static String verificarTipo(Escopos escopos, JanderParser.FatorContext ctx) {
        String ret = null;
        for (var p : ctx.parcela()) {
            String aux = verificarTipo(escopos, p);
            if (ret == null) ret = aux;
            else if (!ret.equals(aux) && !aux.equals("invalido") && !ret.equals("invalido")) {
                if ((ret.equals("inteiro") && aux.equals("real")) || (ret.equals("real") && aux.equals("inteiro"))) {
                    ret = "real";
                } else {
                    ret = "invalido";
                }
            }
        }
        return ret != null ? ret : "invalido";
    }

    public static String verificarTipo(Escopos escopos, JanderParser.ParcelaContext ctx) {
        if (ctx.parcela_unario() != null) return verificarTipo(escopos, ctx.parcela_unario());
        if (ctx.parcela_nao_unario() != null) return verificarTipo(escopos, ctx.parcela_nao_unario());
        return "invalido";
    }

    public static String verificarTipo(Escopos escopos, JanderParser.Parcela_unarioContext ctx) {
        if (ctx.NUM_INT() != null) return "inteiro";
        if (ctx.NUM_REAL() != null) return "real";

        if (ctx.identificador() != null) {
            String tipo = obterTipoIdentificador(escopos, ctx.identificador());
            if (ctx.ENDERECO() != null) {
                if (tipo.startsWith("^")) return tipo.substring(1);
                return "invalido";
            }
            return tipo;
        }

        if (ctx.IDENT() != null && ctx.ABRE_PAR() != null) {
            String nomeFunc = ctx.IDENT().getText();
            TabelaDeSimbolos.EntradaTabelaDeSimbolos entrada = null;
            for (TabelaDeSimbolos tabela : escopos.percorrerEscoposAninhados()) {
                if (tabela.existe(nomeFunc)) {
                    entrada = tabela.verificar(nomeFunc);
                    break;
                }
            }
            if (entrada == null) {
                adicionarErroSemantico(ctx.IDENT().getSymbol(), "identificador " + nomeFunc + " nao declarado");
                return "invalido";
            }

            List<String> argsTipos = new ArrayList<>();
            if (ctx.expressao() != null) {
                for (var exp : ctx.expressao()) argsTipos.add(verificarTipo(escopos, exp));
            }

            if (entrada.parametros.size() != argsTipos.size()) {
                adicionarErroSemantico(ctx.IDENT().getSymbol(), "incompatibilidade de parametros na chamada de " + nomeFunc);
            } else {
                for (int i = 0; i < argsTipos.size(); i++) {
                    if (!compativelParametro(entrada.parametros.get(i), argsTipos.get(i))) {
                        adicionarErroSemantico(ctx.IDENT().getSymbol(), "incompatibilidade de parametros na chamada de " + nomeFunc);
                        break;
                    }
                }
            }
            return entrada.tipo;
        }

        if (ctx.expressao() != null && !ctx.expressao().isEmpty()) {
            return verificarTipo(escopos, ctx.expressao(0));
        }
        return "invalido";
    }

    public static String verificarTipo(Escopos escopos, JanderParser.Parcela_nao_unarioContext ctx) {
        if (ctx.CADEIA() != null) return "literal";
        if (ctx.PONTEIRO() != null && ctx.identificador() != null) {
            String tipoBase = obterTipoIdentificador(escopos, ctx.identificador());
            if (tipoBase.equals("invalido")) return "invalido";
            return "&" + tipoBase;
        }
        return "invalido";
    }
}