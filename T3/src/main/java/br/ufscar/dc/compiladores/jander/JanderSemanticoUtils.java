package br.ufscar.dc.compiladores.jander;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.Token;
import br.ufscar.dc.compiladores.jander.TabelaDeSimbolos.TipoJander;

public class JanderSemanticoUtils {

    // Lista que acumulará todos os erros semânticos encontrados
    public static List<String> errosSemanticos = new ArrayList<>();

    // erro no padrão: "Linha X: mensagem"
    public static void adicionarErroSemantico(Token t, String mensagem) {
        int linha = t.getLine();
        errosSemanticos.add(String.format("Linha %d: %s", linha, mensagem));
    }

    // ==========================================================
    // VALIDAÇÕES BÁSICAS DE TIPO E COMPATIBILIDADE
    // ==========================================================

    public static TipoJander verificarTipo(String strTipo) {
        switch (strTipo) {
            case "inteiro": return TipoJander.INTEIRO;
            case "real": return TipoJander.REAL;
            case "literal": return TipoJander.LITERAL;
            case "logico": return TipoJander.LOGICO;
            default: return TipoJander.INVALIDO;
        }
    }

    public static boolean tiposCompativeis(TipoJander tipoVariavel, TipoJander tipoExpressao) {
        if (tipoVariavel == tipoExpressao) return true;
        if ((tipoVariavel == TipoJander.REAL || tipoVariavel == TipoJander.INTEIRO) &&
                (tipoExpressao == TipoJander.REAL || tipoExpressao == TipoJander.INTEIRO)) {
            return true;
        }
        return false;
    }

    // 1. expressao : termo_logico (OP_OU termo_logico)*
    public static TipoJander verificarTipo(Escopos escopos, JanderParser.ExpressaoContext ctx) {
        TipoJander ret = null;
        for (var tl : ctx.termo_logico()) {
            TipoJander aux = verificarTipo(escopos, tl);
            if (ret == null) {
                ret = aux;
            } else if (ret != aux && aux != TipoJander.INVALIDO) {
                ret = TipoJander.INVALIDO; // Tipos incompatíveis resultam em tipo indefinido
            }
        }
        // Se a expressão contém o operador 'ou', o resultado final obrigatoriamente é lógico
        if (ctx.OP_OU() != null && !ctx.OP_OU().isEmpty()) {
            return TipoJander.LOGICO;
        }
        return ret;
    }

    // 2. termo_logico : fator_logico (OP_E fator_logico)*
    public static TipoJander verificarTipo(Escopos escopos, JanderParser.Termo_logicoContext ctx) {
        TipoJander ret = null;
        for (var fl : ctx.fator_logico()) {
            TipoJander aux = verificarTipo(escopos, fl);
            if (ret == null) {
                ret = aux;
            } else if (ret != aux && aux != TipoJander.INVALIDO) {
                ret = TipoJander.INVALIDO;
            }
        }
        if (ctx.OP_E() != null && !ctx.OP_E().isEmpty()) {
            return TipoJander.LOGICO;
        }
        return ret;
    }

    // 3. fator_logico : (OP_NAO)? parcela_logica
    public static TipoJander verificarTipo(Escopos escopos, JanderParser.Fator_logicoContext ctx) {
        TipoJander tipo = verificarTipo(escopos, ctx.parcela_logica());
        if (ctx.OP_NAO() != null) {
            return TipoJander.LOGICO;
        }
        return tipo;
    }

    // 4. parcela_logica : VERDADEIRO | FALSO | exp_relacional
    public static TipoJander verificarTipo(Escopos escopos, JanderParser.Parcela_logicaContext ctx) {
        if (ctx.VERDADEIRO() != null || ctx.FALSO() != null) {
            return TipoJander.LOGICO;
        }
        return verificarTipo(escopos, ctx.exp_relacional());
    }

    // 5. exp_relacional : exp_aritmetica (op_relacional exp_aritmetica)?
    public static TipoJander verificarTipo(Escopos escopos, JanderParser.Exp_relacionalContext ctx) {
        TipoJander ret = null;
        for (var ea : ctx.exp_aritmetica()) {
            TipoJander aux = verificarTipo(escopos, ea);
            if (ret == null) {
                ret = aux;
            } else if (ret != aux && aux != TipoJander.INVALIDO) {
                boolean numCompativel = (ret == TipoJander.INTEIRO || ret == TipoJander.REAL) &&
                        (aux == TipoJander.INTEIRO || aux == TipoJander.REAL);
                if (!numCompativel) {
                    ret = TipoJander.INVALIDO;
                }
            }
        }
        if (ctx.op_relacional() != null) {
            return TipoJander.LOGICO;
        }
        return ret;
    }

    // 6. exp_aritmetica : termo (op1 termo)*
    public static TipoJander verificarTipo(Escopos escopos, JanderParser.Exp_aritmeticaContext ctx) {
        TipoJander ret = null;
        for (var t : ctx.termo()) {
            TipoJander aux = verificarTipo(escopos, t);
            if (ret == null) {
                ret = aux;
            } else if (ret != aux && aux != TipoJander.INVALIDO) {
                if ((ret == TipoJander.INTEIRO && aux == TipoJander.REAL) ||
                        (ret == TipoJander.REAL && aux == TipoJander.INTEIRO)) {
                    ret = TipoJander.REAL; // Promoção de tipo: Int + Real = Real
                } else {
                    ret = TipoJander.INVALIDO;
                }
            }
        }
        return ret;
    }

    // 7. termo : fator (op2 fator)*
    public static TipoJander verificarTipo(Escopos escopos, JanderParser.TermoContext ctx) {
        TipoJander ret = null;
        for (var f : ctx.fator()) {
            TipoJander aux = verificarTipo(escopos, f);
            if (ret == null) {
                ret = aux;
            } else if (ret != aux && aux != TipoJander.INVALIDO) {
                if ((ret == TipoJander.INTEIRO && aux == TipoJander.REAL) ||
                        (ret == TipoJander.REAL && aux == TipoJander.INTEIRO)) {
                    ret = TipoJander.REAL;
                } else {
                    ret = TipoJander.INVALIDO;
                }
            }
        }
        return ret;
    }

    // 8. fator : parcela (op3 parcela)*
    public static TipoJander verificarTipo(Escopos escopos, JanderParser.FatorContext ctx) {
        TipoJander ret = null;
        for (var p : ctx.parcela()) {
            TipoJander aux = verificarTipo(escopos, p);
            if (ret == null) {
                ret = aux;
            } else if (ret != aux && aux != TipoJander.INVALIDO) {
                if ((ret == TipoJander.INTEIRO && aux == TipoJander.REAL) ||
                        (ret == TipoJander.REAL && aux == TipoJander.INTEIRO)) {
                    ret = TipoJander.REAL;
                } else {
                    ret = TipoJander.INVALIDO;
                }
            }
        }
        return ret;
    }

    // 9. parcela : (op_unario)? parcela_unario | parcela_nao_unario
    public static TipoJander verificarTipo(Escopos escopos, JanderParser.ParcelaContext ctx) {
        if (ctx.parcela_unario() != null) {
            return verificarTipo(escopos, ctx.parcela_unario());
        }
        if (ctx.parcela_nao_unario() != null) {
            return verificarTipo(escopos, ctx.parcela_nao_unario());
        }
        return TipoJander.INVALIDO;
    }

    // 10. parcela_unario : (ENDERECO)? identificador | IDENT '(' ... ')' | NUM_INT | NUM_REAL | '(' expressao ')'
    public static TipoJander verificarTipo(Escopos escopos, JanderParser.Parcela_unarioContext ctx) {
        // Folhas da árvore (Literais puros)
        if (ctx.NUM_INT() != null) return TipoJander.INTEIRO;
        if (ctx.NUM_REAL() != null) return TipoJander.REAL;

        // Avaliação de Variável
        if (ctx.identificador() != null) {
            String nomeVar = ctx.identificador().getText();
            for (TabelaDeSimbolos tabela : escopos.percorrerEscoposAninhados()) {
                if (tabela.existe(nomeVar)) {
                    return tabela.verificar(nomeVar);
                }
            }
            // Se a variável não existe na tabela, lança o erro de "não declarado"
            adicionarErroSemantico(ctx.identificador().start, "identificador " + nomeVar + " nao declarado");
            return TipoJander.INVALIDO;
        }

        // Avaliação de Chamada de Função
        if (ctx.IDENT() != null && ctx.ABRE_PAR() != null) {
            String nomeFunc = ctx.IDENT().getText();
            for (TabelaDeSimbolos tabela : escopos.percorrerEscoposAninhados()) {
                if (tabela.existe(nomeFunc)) {
                    return tabela.verificar(nomeFunc);
                }
            }
            adicionarErroSemantico(ctx.IDENT().getSymbol(), "identificador " + nomeFunc + " nao declarado");
            return TipoJander.INVALIDO;
        }

        // Expressão entre parênteses
        if (ctx.expressao() != null && !ctx.expressao().isEmpty()) {
            return verificarTipo(escopos, ctx.expressao(0));
        }

        return TipoJander.INVALIDO;
    }

    // 11. parcela_nao_unario : PONTEIRO identificador | CADEIA
    public static TipoJander verificarTipo(Escopos escopos, JanderParser.Parcela_nao_unarioContext ctx) {
        if (ctx.CADEIA() != null) {
            return TipoJander.LITERAL;
        }
        if (ctx.identificador() != null) {
            String nomeVar = ctx.identificador().getText();
            for (TabelaDeSimbolos tabela : escopos.percorrerEscoposAninhados()) {
                if (tabela.existe(nomeVar)) {
                    return tabela.verificar(nomeVar); // Retorna o tipo base para o ponteiro
                }
            }
            adicionarErroSemantico(ctx.identificador().start, "identificador " + nomeVar + " nao declarado");
            return TipoJander.INVALIDO;
        }
        return TipoJander.INVALIDO;
    }
}