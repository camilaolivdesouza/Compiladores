package br.ufscar.dc.compiladores.jander;

import java.io.IOException;
import java.io.PrintWriter;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;

public class Principal {

    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Uso: java Principal <entrada> <saida>");
            return;
        }

        try {
            executar(args[0], args[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void executar(String arquivoEntrada, String arquivoSaida) throws IOException {

        CharStream input = CharStreams.fromFileName(arquivoEntrada);
        Jander lexer = new Jander(input);

        PrintWriter writer = new PrintWriter(arquivoSaida);

        Token token;

        while ((token = lexer.nextToken()).getType() != Token.EOF) {

            String tipo = Jander.VOCABULARY.getDisplayName(token.getType());

            // ===== ERROS =====
            if (tipo.equals("COMENTARIO_NAO_FECHADO")) {
                writer.println("Linha " + token.getLine() + ": comentario nao fechado");
                break;
            }

            if (tipo.equals("CADEIA_NAO_FECHADA")) {
                writer.println("Linha " + token.getLine() + ": cadeia literal nao fechada");
                break;
            }

            if (tipo.equals("ERRO")) {
                writer.println("Linha " + token.getLine() + ": " + token.getText() + " - simbolo nao identificado");
                break;
            }

            // tokens
            if (tipo.equals("IDENT") ||
                tipo.equals("NUM_INT") ||
                tipo.equals("NUM_REAL") ||
                tipo.equals("CADEIA")) {

                writer.println("<'" + token.getText() + "'," + tipo + ">");

            } else {
                writer.println("<'" + token.getText() + "','" + token.getText() + "'>");
            }
        }

        writer.close();
    }

    private static boolean isOperador(String tipo) {
        return tipo.equals("OP_ARIT") ||
               tipo.equals("OP_REL") ||
               tipo.equals("OP_LOGICO");
    }
}
