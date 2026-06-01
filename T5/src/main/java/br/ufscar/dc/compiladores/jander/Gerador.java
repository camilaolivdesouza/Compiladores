package br.ufscar.dc.compiladores.jander;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

import java.util.Set;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

public class Gerador extends JanderBaseVisitor<Void> {

    // Buffer que acumula o código C gerado
    public StringBuilder saida;

    // Tabela para rastrear os tipos das variáveis declaradas e adaptar formatadores (%d, %f, %s)
    private Map<String, String> varTypes;

    // Tabela para armazenar as definições de structs
    private Map<String, JanderParser.RegistroContext> userTypes;

    // Controle de indentação para blocos aninhados
    private String indent;

    // Conjunto que armazena os nomes das variáveis passadas por referência
    private Set<String> variaveisReferencia;

    // Estrutura para manter a assinatura das rotinas e identificar parâmetros passados por referência
    class AssinaturaRotina {
        List<Boolean> isVarParam = new ArrayList<>();
    }
    private Map<String, AssinaturaRotina> rotinas;

    public Gerador() {
        saida = new StringBuilder();
        varTypes = new HashMap<>();
        userTypes = new HashMap<>();
        indent = "\t";
        variaveisReferencia = new HashSet<>();
        rotinas = new HashMap<>();
    }

    // Extrai as dimensões de um vetor declarado em LA e o adapta para o tamanho em C
    private String getDimensoes(String tipoLa) {
        if (tipoLa.startsWith("vetor")) {
            int br1 = tipoLa.indexOf('[');
            int br2 = tipoLa.indexOf(']');
            if (br1 != -1 && br2 != -1) {
                String intervalo = tipoLa.substring(br1 + 1, br2);
                String[] parts = intervalo.split("\\.\\.");
                if (parts.length == 2) {
                    try {
                        int size = Integer.parseInt(parts[1].trim()) + 1;
                        return "[" + size + "]";
                    } catch(Exception e) {}
                }
            }
            return "[100]";
        }
        return "";
    }

    // Mapeia os tipos de dados da linguagem LA para os tipos primitivos em C.
    private String getTipoC(String tipoLA) {
        if (tipoLA.startsWith("vetor")) {
            int deIdx = tipoLA.indexOf("de");
            if (deIdx != -1) {
                String tipoBase = tipoLA.substring(deIdx + 2).trim();
                return getTipoC(tipoBase);
            }
        }
        switch (tipoLA) {
            case "inteiro": return "int";
            case "real": return "float";
            case "literal": return "char";
            case "logico": return "int"; // C não usa stdbool por padrão em expressões aritméticas
            default:
                if (tipoLA.startsWith("^")) {
                    return getTipoC(tipoLA.substring(1)) + "*"; // Tratamento de ponteiros
                }
                return tipoLA;
        }
    }

    // Normaliza identificadores removendo índices de array
    private String normalizeNome(String text) {
        return text.replaceAll("\\[.*?\\]", "").trim();
    }

    private String getTipo(String nome) {
        return varTypes.getOrDefault(nome, "inteiro");
    }

    // Adiciona variáveis e campos de structs à tabela de tipos para consultas em comandos de leia/escreva
    private void registerVarType(String baseName, String type, JanderParser.RegistroContext regCtx) {
        if (regCtx != null) {
            for (var campo : regCtx.variavel()) {
                String campoTipo = campo.tipo().getText();
                for (var idCampo : campo.identificador()) {
                    registerVarType(baseName + "." + idCampo.IDENT(0).getText(), campoTipo, campo.tipo().registro());
                }
            }
        } else if (userTypes.containsKey(type)) {
            registerVarType(baseName, type, userTypes.get(type));
        } else {
            varTypes.put(baseName, type);
        }
    }

    // Retorna os tokens originais preservando espaçamentos
    private String getTextWithSpaces(ParseTree tree) {
        if (tree == null) return "";
        if (tree instanceof TerminalNode) return tree.getText() + " ";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tree.getChildCount(); i++) {
            sb.append(getTextWithSpaces(tree.getChild(i)));
        }
        return sb.toString();
    }

    // Formata expressões booleanas e relacionais da linguagem LA para os operadores equivalentes em C
    private String formatarExpressao(ParseTree ctx) {
        String texto = getTextWithSpaces(ctx);
        if (texto.trim().startsWith("\"")) {
            return texto.trim();
        }
        texto = texto.replace("<>", "!=");
        texto = texto.replaceAll("(?<![!<>=])=(?!=)", "==");
        texto = texto.replaceAll("\\be\\b", "&&");
        texto = texto.replaceAll("\\bou\\b", "||");
        texto = texto.replaceAll("\\bnao\\b", "!");
        texto = texto.replaceAll("\\bverdadeiro\\b", "1");
        texto = texto.replaceAll("\\bfalso\\b", "0");
        texto = texto.replace("^", "*");

        // Desreferencia automaticamente variáveis que foram passadas como parâmetro VAR
        for (String varRef : variaveisReferencia) {
            texto = texto.replaceAll("\\b" + varRef + "\\b", "(*" + varRef + ")");
        }

        return texto.trim();
    }

    // Tenta identificar o tipo de retorno/formatação predominante de uma expressão
    private String inferirTipoExpressaoRaw(String exprRaw) {
        java.util.regex.Matcher m = java.util.regex.Pattern
                .compile("([a-zA-Z_][a-zA-Z_0-9]*(?:\\.[a-zA-Z_][a-zA-Z_0-9]*)*)")
                .matcher(exprRaw);
        boolean hasReal = false;
        boolean hasLiteral = false;
        while (m.find()) {
            String match = normalizeNome(m.group(1));
            if (varTypes.containsKey(match)) {
                String t = getTipo(match);
                if (t.contains("real")) hasReal = true;
                if (t.contains("literal")) hasLiteral = true;
            }
        }
        if (hasLiteral) return "literal";
        if (hasReal) return "real";
        return "inteiro";
    }

    // Regra inicial: Adiciona as bibliotecas padrão do C e estrutura a função main
    @Override
    public Void visitPrograma(JanderParser.ProgramaContext ctx) {
        saida.append("#include <stdio.h>\n");
        saida.append("#include <stdlib.h>\n");
        saida.append("#include <string.h>\n\n");

        if (ctx.declaracoes() != null) {
            visitDeclaracoes(ctx.declaracoes());
        }

        saida.append("int main() {\n");
        visitCorpo(ctx.corpo());
        saida.append("\treturn 0;\n");
        saida.append("}\n");
        return null;
    }

    @Override
    public Void visitDeclaracoes(JanderParser.DeclaracoesContext ctx) {
        for (var decl : ctx.decl_local_global()) {
            if (decl.declaracao_local() != null) {
                visitDeclaracao_local(decl.declaracao_local());
            } else if (decl.declaracao_global() != null) {
                visitDeclaracao_global(decl.declaracao_global());
            }
        }
        return null;
    }

    // Processa funções e procedimentos globais
    @Override
    public Void visitDeclaracao_global(JanderParser.Declaracao_globalContext ctx) {
        variaveisReferencia.clear(); // Limpa as referências de VAR a cada escopo
        if (ctx.PROCEDIMENTO() != null) {
            String nomeRotina = ctx.IDENT().getText();
            saida.append("void ").append(nomeRotina).append("(");
            gerarParametros(nomeRotina, ctx.parametros());
            saida.append(") {\n");
            for (var decl : ctx.declaracao_local()) visitDeclaracao_local(decl);
            for (var cmd  : ctx.cmd())              visitCmd(cmd);
            saida.append("}\n\n");
        } else if (ctx.FUNCAO() != null) {
            String nomeRotina = ctx.IDENT().getText();
            String tipoRetornoLa = ctx.tipo_estendido().getText().replaceAll("\\s", "");
            varTypes.put(nomeRotina, tipoRetornoLa);
            saida.append(getTipoC(tipoRetornoLa)).append(" ").append(nomeRotina).append("(");
            gerarParametros(nomeRotina, ctx.parametros());
            saida.append(") {\n");
            for (var decl : ctx.declaracao_local()) visitDeclaracao_local(decl);
            for (var cmd  : ctx.cmd())              visitCmd(cmd);
            saida.append("}\n\n");
        }
        return null;
    }

    // Regra auxiliar para instanciar as assinaturas de parâmetros
    private void gerarParametros(String nomeRotina, JanderParser.ParametrosContext ctx) {
        AssinaturaRotina sig = new AssinaturaRotina();
        rotinas.put(nomeRotina, sig);
        if (ctx == null) return;
        boolean first = true;
        for (var param : ctx.parametro()) {
            String tipoLa = param.tipo_estendido().getText().replaceAll("\\s", "");
            String tipoC  = getTipoC(tipoLa);
            boolean isVar = param.VAR() != null; // Verifica se é passado por referência

            for (var ident : param.identificador()) {
                if (!first) saida.append(", ");
                first = false;

                String paramName = ident.getText();
                registerVarType(normalizeNome(paramName), tipoLa, null);
                sig.isVarParam.add(isVar);

                if (isVar) {
                    variaveisReferencia.add(normalizeNome(paramName));
                }

                saida.append(tipoC);
                // Parâmetros tipo string ou var exigem um ponteiro extra
                if (tipoLa.contains("literal")) {
                    saida.append("*");
                } else if (isVar && !tipoC.endsWith("*")) {
                    saida.append("*");
                }
                saida.append(" ").append(paramName);
            }
        }
    }

    // Declarações locais, tratando structs, typedefs e constantes
    @Override
    public Void visitDeclaracao_local(JanderParser.Declaracao_localContext ctx) {
        if (ctx.DECLARE() != null) {
            String tipoLaRaw = ctx.variavel().tipo().getText();
            String tipoLa = tipoLaRaw.replaceAll("\\s", "");
            String tipoC  = getTipoC(tipoLa);
            String dimensoes = getDimensoes(tipoLa);

            for (var identCtx : ctx.variavel().identificador()) {
                String baseName = normalizeNome(identCtx.getText());
                registerVarType(baseName, tipoLa, ctx.variavel().tipo().registro());
            }

            if (ctx.variavel().tipo().registro() != null) {
                // Estruturação inline para registros
                for (var identCtx : ctx.variavel().identificador()) {
                    saida.append(indent).append("struct {\n");
                    gerarCamposRegistro(ctx.variavel().tipo().registro());
                    saida.append(indent).append("} ").append(identCtx.getText()).append(dimensoes).append(";\n");
                }
            } else {
                for (var identCtx : ctx.variavel().identificador()) {
                    String identStr = identCtx.getText();
                    saida.append(indent).append(tipoC).append(" ").append(identStr).append(dimensoes);
                    if (tipoLa.contains("literal")) saida.append("[80]"); // Limite de array para cadeias de caracteres
                    saida.append(";\n");
                }
            }
        } else if (ctx.TIPO() != null) {
            String nomeTipo = ctx.IDENT().getText();
            String tipoLa = ctx.tipo().getText().replaceAll("\\s", "");
            String tipoC = getTipoC(tipoLa);
            String dimensoes = getDimensoes(tipoLa);

            if (ctx.tipo().registro() != null) {
                userTypes.put(nomeTipo, ctx.tipo().registro());
                saida.append(indent).append("typedef struct {\n");
                gerarCamposRegistro(ctx.tipo().registro());
                saida.append(indent).append("} ").append(nomeTipo).append(dimensoes).append(";\n");
            } else {
                saida.append(indent).append("typedef ")
                        .append(tipoC).append(" ").append(nomeTipo).append(dimensoes).append(";\n");
            }
        } else if (ctx.CONSTANTE() != null) {
            saida.append(indent).append("#define ")
                    .append(ctx.IDENT().getText())
                    .append(" ").append(ctx.valor_constante().getText()).append("\n");
        }
        return null;
    }

    private void gerarCamposRegistro(JanderParser.RegistroContext reg) {
        for (var campo : reg.variavel()) {
            String tipoCampoLa = campo.tipo().getText().replaceAll("\\s", "");
            String tipoCampoC  = getTipoC(tipoCampoLa);
            String dimensoes = getDimensoes(tipoCampoLa);
            if (campo.tipo().registro() != null) {
                for (var idCampo : campo.identificador()) {
                    saida.append(indent).append("\tstruct {\n");
                    gerarCamposRegistro(campo.tipo().registro());
                    saida.append(indent).append("\t} ").append(idCampo.getText()).append(dimensoes).append(";\n");
                }
            } else {
                for (var idCampo : campo.identificador()) {
                    saida.append(indent).append("\t").append(tipoCampoC)
                            .append(" ").append(idCampo.getText()).append(dimensoes);
                    if (tipoCampoLa.contains("literal")) saida.append("[80]");
                    saida.append(";\n");
                }
            }
        }
    }

    private void gerarCamposRegistroGlobal(JanderParser.RegistroContext reg) {
        for (var campo : reg.variavel()) {
            String tipoCampoLa = campo.tipo().getText().replaceAll("\\s", "");
            String tipoCampoC  = getTipoC(tipoCampoLa);
            String dimensoes = getDimensoes(tipoCampoLa);
            if (campo.tipo().registro() != null) {
                for (var idCampo : campo.identificador()) {
                    saida.append("\tstruct {\n");
                    gerarCamposRegistroGlobal(campo.tipo().registro());
                    saida.append("\t} ").append(idCampo.getText()).append(dimensoes).append(";\n");
                }
            } else {
                for (var idCampo : campo.identificador()) {
                    saida.append("\t").append(tipoCampoC)
                            .append(" ").append(idCampo.getText()).append(dimensoes);
                    if (tipoCampoLa.contains("literal")) saida.append("[80]");
                    saida.append(";\n");
                }
            }
        }
    }

    // Mapeia o 'escreva' para a função printf
    @Override
    public Void visitCmdEscreva(JanderParser.CmdEscrevaContext ctx) {
        for (var expressao : ctx.expressao()) {
            String exprStr = formatarExpressao(expressao);
            String exprRaw = expressao.getText();

            if (exprStr.startsWith("\"")) {
                saida.append(indent).append("printf(").append(exprStr).append(");\n");
            } else {
                String tipo = inferirTipoExpressaoRaw(exprRaw);
                String fmt;
                switch (tipo) {
                    case "real":    fmt = "%f"; break;
                    case "literal": fmt = "%s"; break;
                    default:        fmt = "%d"; break;
                }
                saida.append(indent)
                        .append("printf(\"").append(fmt).append("\", ")
                        .append(exprStr).append(");\n");
            }
        }
        return null;
    }

    // Mapeia o 'leia' para gets() (se literal) ou scanf().
    @Override
    public Void visitCmdLeia(JanderParser.CmdLeiaContext ctx) {
        for (int i = 0; i < ctx.identificador().size(); i++) {
            JanderParser.IdentificadorContext identCtx = ctx.identificador(i);
            String varNomeFmt = formatarExpressao(identCtx);
            String nomeBase = normalizeNome(identCtx.getText());
            String tipo = getTipo(nomeBase);

            boolean hasEndereco = false;
            for (int j = 0; j < ctx.getChildCount(); j++) {
                if (ctx.getChild(j) == identCtx) {
                    if (j > 0 && ctx.getChild(j-1).getText().equals("^")) {
                        hasEndereco = true;
                    }
                    break;
                }
            }

            if (tipo.contains("literal")) {
                saida.append(indent).append("gets(").append(varNomeFmt).append(");\n");
            } else if (tipo.contains("real")) {
                // Se o usuário passou ^p no leia, ignorar o '&' no scanf
                if (hasEndereco) saida.append(indent).append("scanf(\"%f\", ").append(varNomeFmt).append(");\n");
                else saida.append(indent).append("scanf(\"%f\", &").append(varNomeFmt).append(");\n");
            } else {
                if (hasEndereco) saida.append(indent).append("scanf(\"%d\", ").append(varNomeFmt).append(");\n");
                else saida.append(indent).append("scanf(\"%d\", &").append(varNomeFmt).append(");\n");
            }
        }
        return null;
    }

    // Tratamento de atribuições (<-). Identifica se 'strcpy' é necessário para manipulação de strings (literal)
    @Override
    public Void visitCmdAtribuicao(JanderParser.CmdAtribuicaoContext ctx) {
        String ptr     = ctx.getText().startsWith("^") ? "*" : "";
        String varNome = formatarExpressao(ctx.identificador());
        String expr    = formatarExpressao(ctx.expressao());
        String tipo    = getTipo(normalizeNome(ctx.identificador().getText()));

        if (tipo.contains("literal") && !ctx.getText().startsWith("^")) {
            saida.append(indent).append("strcpy(").append(varNome).append(", ").append(expr).append(");\n");
        } else {
            saida.append(indent).append(ptr).append(varNome).append(" = ").append(expr).append(";\n");
        }
        return null;
    }

    // Mapeia 'se ... entao' para 'if (cond) { ... }' e o senao
    @Override
    public Void visitCmdSe(JanderParser.CmdSeContext ctx) {
        String condicao = formatarExpressao(ctx.expressao());
        saida.append(indent).append("if (").append(condicao).append(") {\n");

        boolean emSenao = false;
        for (ParseTree child : ctx.children) {
            String childText = child.getText();
            if (childText.equals("senao")) {
                saida.append(indent).append("} else {\n");
                emSenao = true;
            } else if (child instanceof JanderParser.CmdContext) {
                visitCmd((JanderParser.CmdContext) child);
            }
        }
        saida.append(indent).append("}\n");
        return null;
    }

    // Traduz comandos switch-case baseados em ranges.
    @Override
    public Void visitCmdCaso(JanderParser.CmdCasoContext ctx) {
        String exp = formatarExpressao(ctx.exp_aritmetica());
        saida.append(indent).append("switch (").append(exp).append(") {\n");

        for (var selecao : ctx.selecao().item_selecao()) {
            for (var intervalo : selecao.constantes().numero_intervalo()) {
                if (intervalo.INTERVALO() != null) {
                    // GCC Extension: case x ... y:
                    String aStr = getNumeroStr(intervalo, 0);
                    String bStr = getNumeroStr(intervalo, 1);
                    saida.append(indent).append("\tcase ").append(aStr)
                            .append(" ... ").append(bStr).append(":\n");
                } else {
                    String num = getNumeroStr(intervalo, 0);
                    saida.append(indent).append("\tcase ").append(num).append(":\n");
                }
            }
            for (var cmd : selecao.cmd()) visitCmd(cmd);
            saida.append(indent).append("\t\tbreak;\n");
        }

        boolean emSenao = false;
        for (ParseTree child : ctx.children) {
            if (child.getText().equals("senao")) {
                saida.append(indent).append("\tdefault:\n");
                emSenao = true;
            } else if (emSenao && child instanceof JanderParser.CmdContext) {
                visitCmd((JanderParser.CmdContext) child);
            }
        }
        if (emSenao) saida.append(indent).append("\t\tbreak;\n");

        saida.append(indent).append("}\n");
        return null;
    }

    private String getNumeroStr(JanderParser.Numero_intervaloContext ctx, int index) {
        if (index == 0) {
            boolean negativo = ctx.getChildCount() > 0
                    && ctx.getChild(0) instanceof JanderParser.Op_unarioContext;
            return (negativo ? "-" : "") + ctx.NUM_INT(0).getText();
        } else {
            int idxPonto = -1;
            for (int i = 0; i < ctx.getChildCount(); i++) {
                if (ctx.getChild(i).getText().equals("..")) {
                    idxPonto = i;
                    break;
                }
            }
            if (idxPonto == -1) return "";
            boolean negativo = idxPonto + 1 < ctx.getChildCount()
                    && ctx.getChild(idxPonto + 1) instanceof JanderParser.Op_unarioContext;
            return (negativo ? "-" : "") + ctx.NUM_INT(1).getText();
        }
    }

    // Constrói loops 'for' com iterações baseadas em limites de variáveis declaradas
    @Override
    public Void visitCmdPara(JanderParser.CmdParaContext ctx) {
        String id    = ctx.IDENT().getText();
        String start = formatarExpressao(ctx.exp_aritmetica(0));
        String end   = formatarExpressao(ctx.exp_aritmetica(1));

        saida.append(indent).append("for (")
                .append(id).append(" = ").append(start).append("; ")
                .append(id).append(" <= ").append(end).append("; ")
                .append(id).append("++) {\n");
        for (var cmd : ctx.cmd()) visitCmd(cmd);
        saida.append(indent).append("}\n");
        return null;
    }

    // Mapeia 'faca...ate' do LA para o loop 'do-while' em C
    @Override
    public Void visitCmdFaca(JanderParser.CmdFacaContext ctx) {
        saida.append(indent).append("do {\n");
        for (var cmd : ctx.cmd()) visitCmd(cmd);
        String cond = formatarExpressao(ctx.expressao());
        saida.append(indent).append("} while (").append(cond).append(");\n");
        return null;
    }

    // Mapeia 'enquanto...' para o padrão 'while ()'
    @Override
    public Void visitCmdEnquanto(JanderParser.CmdEnquantoContext ctx) {
        String cond = formatarExpressao(ctx.expressao());
        saida.append(indent).append("while (").append(cond).append(") {\n");
        for (var cmd : ctx.cmd()) visitCmd(cmd);
        saida.append(indent).append("}\n");
        return null;
    }

    // Garante que chamadas de função identifiquem os parâmetros passados por referência e enviem com & na frente
    @Override
    public Void visitCmdChamada(JanderParser.CmdChamadaContext ctx) {
        String nome = ctx.IDENT().getText();
        saida.append(indent).append(nome).append("(");
        AssinaturaRotina sig = rotinas.get(nome);
        boolean first = true;
        int pIdx = 0;

        if (ctx.expressao() != null) {
            for (var exp : ctx.expressao()) {
                if (!first) saida.append(", ");
                String expStr = formatarExpressao(exp);

                // Se o parâmetro alvo for tipo var (passagem por referência), envia o endereço
                if (sig != null && pIdx < sig.isVarParam.size() && sig.isVarParam.get(pIdx)) {
                    saida.append("&(").append(expStr).append(")");
                } else {
                    saida.append(expStr);
                }

                first = false;
                pIdx++;
            }
        }
        saida.append(");\n");
        return null;
    }

    @Override
    public Void visitCmdRetorne(JanderParser.CmdRetorneContext ctx) {
        saida.append(indent)
                .append("return ").append(formatarExpressao(ctx.expressao())).append(";\n");
        return null;
    }
}