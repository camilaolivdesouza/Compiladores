package br.ufscar.dc.compiladores.receita;

import java.io.IOException;
import java.io.PrintWriter;
import org.antlr.v4.runtime.*;
import java.util.Arrays;
import java.util.List;

public class Principal {

    public static void main(String[] args) throws IOException {

        if (args.length < 2 || args.length > 3) {
            System.out.println("Uso: entrada.txt saida.html [restricao]");
            return;
        }

        String entrada = args[0];
        String arquivoSaida = args[1];
        String restricao = args.length == 3 ? args[2] : null;

        try {
            CharStream cs = CharStreams.fromFileName(entrada);
            ReceitaLexer lexer = new ReceitaLexer(cs);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            tokens.fill();

            // 1. Verificação Léxica
            for (Token t : tokens.getTokens()) {
                String nome = ReceitaLexer.VOCABULARY.getSymbolicName(t.getType());

                if ("CARACTERE_INVALIDO".equals(nome)) {
                    String erro = String.format("Linha %d: %s - simbolo nao identificado", t.getLine(), t.getText());
                    System.out.println(erro);
                    System.out.println("Fim da compilacao");
                    GeradorReceita.gerarPaginaDeErro(arquivoSaida, List.of(erro));
                    return;
                }

                if ("ERRO_CADEIA".equals(nome)) {
                    String erro = String.format("Linha %d: cadeia literal nao fechada", t.getLine());
                    System.out.println(erro);
                    System.out.println("Fim da compilacao");
                    GeradorReceita.gerarPaginaDeErro(arquivoSaida, List.of(erro));
                    return;
                }
            }

            ReceitaParser parser = new ReceitaParser(tokens);
            parser.removeErrorListeners();

            // 2. Verificação Sintática
            String[] erroSintatico = {null};
            parser.addErrorListener(new BaseErrorListener() {
                @Override
                public void syntaxError(
                        Recognizer<?, ?> recognizer,
                        Object offendingSymbol,
                        int line,
                        int pos,
                        String msg,
                        RecognitionException e
                ) {
                    Token token = (Token) offendingSymbol;
                    erroSintatico[0] = String.format("Linha %d: erro sintatico proximo a %s", line, token.getText());
                }
            });

            ReceitaParser.ReceitaContext arvore = parser.receita();

            if (erroSintatico[0] != null) {
                System.out.println(erroSintatico[0]);
                System.out.println("Fim da compilacao");
                GeradorReceita.gerarPaginaDeErro(arquivoSaida, List.of(erroSintatico[0]));
                return;
            }

            // Valida se a restrição passada via terminal existe na linguagem
            if (restricao != null) {
                List<String> restricoesValidas = Arrays.asList("vegano", "vegetariano", "lactose", "gluten");

                if (!restricoesValidas.contains(restricao.toLowerCase())) {
                    String erro = "Restrição alimentar \"" + restricao + "\" não reconhecida.";
                    System.out.println(erro);
                    System.out.println("Fim da compilacao");
                    GeradorReceita.gerarPaginaDeErro(arquivoSaida, List.of(erro));
                    return;
                }
            }

            // 3. Verificação Semântica
            ReceitaSemanticoUtils.limparErros();
            ReceitaSemantico semantico = new ReceitaSemantico();
            semantico.visitReceita(arvore);

            if (!ReceitaSemanticoUtils.errosSemanticos.isEmpty()) {
                for (String erro : ReceitaSemanticoUtils.errosSemanticos) {
                    System.out.println(erro);
                }
                System.out.println("Fim da compilacao");
                GeradorReceita.gerarPaginaDeErro(arquivoSaida, ReceitaSemanticoUtils.errosSemanticos);
                return;
            }

            // 4. Geração de Código HTML
            // Passamos a tabela preenchida pelo semântico para o gerador
            GeradorReceita gerador = new GeradorReceita(semantico.tabela, restricao);
            gerador.visitReceita(arvore);

            try (PrintWriter out = new PrintWriter(arquivoSaida)) {
                out.print(gerador.getHtmlGerado());
            }

            // 5. Mensagens finais
            if (restricao == null) {
                System.out.println("Receita processada sem adaptações alimentares.");
            } else {
                System.out.println("Restricao solicitada processada: " + restricao);
            }
            System.out.println("Fim da compilacao");

        } catch (Exception e) {
            String erro = "Erro interno: " + e.getMessage();
            System.out.println(erro);
            System.out.println("Fim da compilacao");
            GeradorReceita.gerarPaginaDeErro(arquivoSaida, List.of(erro));
        }
    }
}