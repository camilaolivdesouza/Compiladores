package br.ufscar.dc.compiladores.receita;

import java.io.IOException;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class Principal {

    public static void main(String[] args) throws IOException {

        if (args.length < 2 || args.length > 3) {

            System.out.println(
                "Uso: java -jar compilador.jar entrada.txt saida.html [restricao]"
            );

            return;
        }

        String entrada = args[0];
        String saida = args[1];

        String restricao = null;

        if (args.length == 3) {
            restricao = args[2];
        }

        try {

            CharStream cs =
                CharStreams.fromFileName(entrada);

            ReceitaLexer lexer =
                new ReceitaLexer(cs);

            CommonTokenStream tokens =
                new CommonTokenStream(lexer);

            ReceitaParser parser =
                new ReceitaParser(tokens);

            parser.removeErrorListeners();

            parser.addErrorListener(
                new BaseErrorListener() {

                    @Override
                    public void syntaxError(
                            Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line,
                            int charPositionInLine,
                            String msg,
                            RecognitionException e
                    ) {

                        System.out.printf(
                            "Linha %d:%d erro sintatico%n",
                            line,
                            charPositionInLine
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
                "Saida: "
                + saida
            );

            System.out.println(
                "Fim da compilacao"
            );

        }

        catch (Exception e) {

            System.out.println(
                e.getMessage()
            );

            System.out.println(
                "Fim da compilacao"
            );

        }

    }

}