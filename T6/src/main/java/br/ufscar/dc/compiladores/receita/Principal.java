package br.ufscar.dc.compiladores.receita;

import java.io.IOException;

import org.antlr.v4.runtime.*;

public class Principal {

    public static void main(String[] args) throws IOException {

        if (args.length < 2 || args.length > 3) {

            System.out.println(
                "Uso: entrada.txt saida.html [restricao]"
            );

            return;
        }

        String entrada = args[0];

        String restricao =
            args.length == 3
                ? args[2]
                : null;

        try {

            CharStream cs =
                CharStreams.fromFileName(
                    entrada
                );

            ReceitaLexer lexer =
                new ReceitaLexer(cs);

            CommonTokenStream tokens =
                new CommonTokenStream(lexer);

            tokens.fill();

            for (Token t : tokens.getTokens()) {

                String nome =
                    ReceitaLexer.VOCABULARY
                        .getSymbolicName(
                            t.getType()
                        );

                if ("CARACTERE_INVALIDO"
                        .equals(nome)) {

                    System.out.printf(
                        "Linha %d: %s - simbolo nao identificado%n",
                        t.getLine(),
                        t.getText()
                    );

                    System.out.println(
                        "Fim da compilacao"
                    );

                    return;

                }

                if ("ERRO_CADEIA"
                        .equals(nome)) {

                    System.out.printf(
                        "Linha %d: cadeia literal nao fechada%n",
                        t.getLine()
                    );

                    System.out.println(
                        "Fim da compilacao"
                    );

                    return;

                }

            }

            ReceitaParser parser =
                new ReceitaParser(
                    tokens
                );

            parser.removeErrorListeners();

            parser.addErrorListener(
                new BaseErrorListener() {

                    @Override
                    public void syntaxError(
                            Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line,
                            int pos,
                            String msg,
                            RecognitionException e
                    ) {

                        Token token =
                            (Token)
                                offendingSymbol;

                        System.out.printf(
                            "Linha %d: erro sintatico proximo a %s%n",
                            line,
                            token.getText()
                        );

                        System.out.println(
                            "Fim da compilacao"
                        );

                        System.exit(1);

                    }

                }
            );

            parser.receita();

            System.out.println(
                "Receita analisada com sucesso."
            );

            if (restricao == null) {

                System.out.println(
                    "Receita processada sem adaptacoes alimentares."
                );

            } else {

                System.out.println(
                    "Restricao solicitada: "
                    + restricao
                );

            }

            System.out.println(
                "Fim da compilacao"
            );

        }

        catch (Exception e) {

            System.out.println(
                "Erro interno: "
                + e.getMessage()
            );

            System.out.println(
                "Fim da compilacao"
            );

        }

    }

}