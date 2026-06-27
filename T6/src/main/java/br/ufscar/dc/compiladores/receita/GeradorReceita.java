package br.ufscar.dc.compiladores.receita;

import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;
import java.io.IOException;

public class GeradorReceita extends ReceitaBaseVisitor<Void> {

    private StringBuilder saida;
    private TabelaDeSimbolos tabela;
    private String restricao;
    private List<String> substituicoesAplicadas;

    public GeradorReceita(TabelaDeSimbolos tabela, String restricao) {
        this.saida = new StringBuilder();
        this.tabela = tabela;
        this.restricao = restricao;
        this.substituicoesAplicadas = new ArrayList<>();
    }

    public String getHtmlGerado() {
        return saida.toString();
    }

    // gera a estrutura base e o relatório de substituições
    @Override
    public Void visitReceita(ReceitaParser.ReceitaContext ctx) {
        saida.append("<!DOCTYPE html>\n");
        saida.append("<html lang=\"pt-BR\">\n");
        saida.append("<head>\n");
        saida.append("    <meta charset=\"UTF-8\">\n");
        saida.append("    <title>Receita Compilada</title>\n");
        saida.append("    <link rel=\"stylesheet\" href=\"estilos.css\">\n");
        saida.append("</head>\n");
        saida.append("<body>\n");

        saida.append("    <header class=\"barra-superior\">\n");
        saida.append("        <h2>Gerador de Receitas: Compiladores 2026</h2>\n");
        saida.append("        <svg xmlns=\"http://www.w3.org/2000/svg\" height=\"35px\" viewBox=\"0 -960 960 960\" width=\"35px\" fill=\"#fcfcfc\">\n");
        saida.append("            <path d=\"M360-400h80v-200h-80v200Zm-160-60q-46-23-73-66.5T100-621q0-75 51.5-127T278-800q12 0 24.5 2t24.5 5q25-41 65-64t88-23q48 0 88 23t65 64q12-3 24-5t25-2q75 0 126.5 52T860-621q0 51-27 94.5T760-460v220H200v-220Zm320 60h80v-200h-80v200Zm-240 80h400v-189l44-22q26-13 41-36.5t15-52.5q0-42-28.5-71T682-720q-11 0-20 2t-19 5l-47 13-31-52q-14-23-36.5-35.5T480-800q-26 0-48.5 12.5T395-752l-31 52-48-13q-10-2-19.5-4.5T277-720q-41 0-69 29t-28 71q0 29 15 52.5t41 36.5l44 22v189Zm-80 80h80v80h400v-80h80v160H200v-160Zm280-80Z\"/>\n");
        saida.append("        </svg>\n");

        saida.append("    </header>\n");
        saida.append("    <main class=\"container\">\n");

        super.visitReceita(ctx);

        // relatório de substituições
        if (this.restricao != null) {
            if (substituicoesAplicadas.isEmpty()) {
                System.out.println("Receita não possui adaptações para a restrição alimentar solicitada.");
            } else {
                saida.append("        <h2 class=\"secao-titulo\">Substituições por restrição ").append(this.restricao).append("</h2>\n");
                saida.append("        <ul>\n");
                for (String sub : substituicoesAplicadas) {
                    saida.append("            <li>").append(sub).append("</li>\n");
                }
                saida.append("        </ul>\n");
            }
        }

        saida.append("    </main>\n");
        saida.append("</body>\n");
        saida.append("</html>\n");

        return null;
    }

    // extrai o nome da receita e as porções
    @Override
    public Void visitCabecalho(ReceitaParser.CabecalhoContext ctx) {
        String nomeReceita = ctx.STRING().getText().replace("\"", "");
        // informação das porções
        String porcoes = ctx.NUMERO().getText();
        saida.append("        <h1 class=\"titulo-receita\"><span>").append(nomeReceita).append("</span></h1>\n");
        saida.append("        <p style=\"text-align: center; font-size: 1.1em; color: #666; margin-top: -30px; margin-bottom: 40px;\">")
                .append("<strong>Porções:</strong> ").append(porcoes)
                .append("</p>\n");

        return super.visitCabecalho(ctx);
    }

    @Override
    public Void visitIngredientes(ReceitaParser.IngredientesContext ctx) {
        saida.append("        <h2 class=\"secao-titulo\">Ingredientes</h2>\n");
        saida.append("        <ul>\n");
        super.visitIngredientes(ctx);
        saida.append("        </ul>\n");
        return null;
    }

    @Override
    public Void visitIngrediente(ReceitaParser.IngredienteContext ctx) {
        String quantidade = ctx.NUMERO().getText();
        String unidade = ctx.unidade() != null ? ctx.unidade().getText() : "";
        String nomeOriginal = ctx.STRING().getText();

        String nomeParaUsar = nomeOriginal;

        if (this.restricao != null) {
            DefinicaoIngrediente def = tabela.obter(nomeOriginal);
            if (def != null) {
                String substituto = def.getSubstituto(this.restricao);
                if (substituto != null) {
                    nomeParaUsar = substituto;
                    String registroSub = nomeOriginal + " &rarr; " + substituto;
                    if (!substituicoesAplicadas.contains(registroSub)) {
                        substituicoesAplicadas.add(registroSub);
                    }
                }
            }
        }

        saida.append("            <li>")
                .append(quantidade).append(" ")
                .append(unidade).append(" de ").append(nomeParaUsar)
                .append("</li>\n");

        return super.visitIngrediente(ctx);
    }

    @Override
    public Void visitPassos(ReceitaParser.PassosContext ctx) {
        saida.append("        <h2 class=\"secao-titulo\">Passo a passo</h2>\n");
        saida.append("        <ul>\n");
        super.visitPassos(ctx);
        saida.append("        </ul>\n");
        return null;
    }

    @Override
    public Void visitPasso(ReceitaParser.PassoContext ctx) {
        String acao = ctx.acao().getText();
        saida.append("            <li><strong>").append(acao).append("</strong> ");

        if (ctx.argumentos() != null) {
            List<String> argsFormatados = new ArrayList<>();

            for (ReceitaParser.ArgumentoContext argCtx : ctx.argumentos().argumento()) {
                if (argCtx.STRING() != null) {
                    String nomeOriginal = argCtx.STRING().getText();
                    String nomeParaUsar = nomeOriginal;

                    if (this.restricao != null) {
                        DefinicaoIngrediente def = tabela.obter(nomeOriginal);
                        if (def != null && def.getSubstituto(this.restricao) != null) {
                            nomeParaUsar = def.getSubstituto(this.restricao);
                        }
                    }
                    argsFormatados.add(nomeParaUsar);
                } else {
                    String num = argCtx.NUMERO().getText();
                    String uni = argCtx.unidade() != null ? argCtx.unidade().getText() : "";
                    argsFormatados.add(num + " " + uni);
                }
            }
            saida.append(String.join(", ", argsFormatados));
        }

        saida.append("</li>\n");
        return super.visitPasso(ctx);
    }

    // página HTML com lista de erros quando a compilação falha
    public static void gerarPaginaDeErro(String arquivoSaida, List<String> erros) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n");
        sb.append("<html lang=\"pt-BR\">\n");
        sb.append("<head>\n");
        sb.append("    <meta charset=\"UTF-8\">\n");
        sb.append("    <title>Erro de Compilação</title>\n");
        sb.append("    <link rel=\"stylesheet\" href=\"estilos.css\">\n");
        sb.append("</head>\n");
        sb.append("<body>\n");
        sb.append("    <header class=\"barra-superior\">\n");
        sb.append("        <h2>Gerador de Receitas: Compiladores 2026</h2>\n");
        sb.append("    </header>\n");
        sb.append("    <main class=\"container\">\n");
        sb.append("        <h1 class=\"titulo-receita\"><span>Erro de Compilação</span></h1>\n");
        sb.append("        <ul>\n");
        for (String erro : erros) {
            sb.append("            <li>").append(erro).append("</li>\n");
        }
        sb.append("        </ul>\n");
        sb.append("    </main>\n");
        sb.append("</body>\n");
        sb.append("</html>\n");

        try (PrintWriter out = new PrintWriter(arquivoSaida)) {
            out.print(sb.toString());
        } catch (IOException e) {
            // não foi possível escrever o HTML de erro
        }
    }
}