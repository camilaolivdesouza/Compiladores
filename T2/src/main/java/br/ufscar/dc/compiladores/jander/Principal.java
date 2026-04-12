package br.ufscar.dc.compiladores.jander;

import java.io.IOException;
import java.io.PrintWriter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

public class Principal {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java Principal <entrada> <saida>");
            return;
        }

        try (PrintWriter writer = new PrintWriter(args[1])) {
            CharStream input = CharStreams.fromFileName(args[0]);
            JanderLexer lexer = new JanderLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            boolean encontrouErroLexico = false;
            Token t;

            while ((t = lexer.nextToken()).getType() != Token.EOF) {
                String nomeToken = JanderLexer.VOCABULARY.getDisplayName(t.getType());

                if (nomeToken.equals("COMENTARIO_NAO_FECHADO")) {
                    writer.println("Linha " + t.getLine() + ": comentario nao fechado");
                    encontrouErroLexico = true;
                    break;
                } else if (nomeToken.equals("CADEIA_NAO_FECHADA")) {
                    writer.println("Linha " + t.getLine() + ": cadeia literal nao fechada");
                    encontrouErroLexico = true;
                    break;
                } else if (nomeToken.equals("ERRO")) {
                    writer.println("Linha " + t.getLine() + ": " + t.getText() + " - simbolo nao identificado");
                    encontrouErroLexico = true;
                    break;
                }
            }

            // --- VERIFICAÇÃO SINTÁTICA (Só ocorre se o léxico estiver OK) ---
            if (!encontrouErroLexico) {
                lexer.reset(); // Volta o lexer para o início para o parser ler
                tokens.setTokenSource(lexer);

                JanderParser parser = new JanderParser(tokens);
                parser.removeErrorListeners();

                // Usa o seu ErrorIdentifier para erros sintáticos
                ErrorIdentifier sintaticoListener = new ErrorIdentifier(writer);
                parser.addErrorListener(sintaticoListener);

                parser.programa();
            }

            writer.println("Fim da compilacao");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}