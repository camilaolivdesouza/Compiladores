package br.ufscar.dc.compiladores.jander;

import java.io.PrintWriter;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

public class ErrorIdentifier extends BaseErrorListener {
    private final PrintWriter writer;
    private boolean jaImprimiuErro = false;

    public ErrorIdentifier(PrintWriter writer) {
        this.writer = writer;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine, String msg, RecognitionException e)
    {
        if (!jaImprimiuErro) {
            if (offendingSymbol == null) {
                // Tratamento para ERROS LÉXICOS
                if (msg.contains("token recognition error at: '\"'")) {
                    writer.println("Linha " + line + ": cadeia literal nao fechada");
                } else if (msg.contains("token recognition error at: '{'")) {
                    writer.println("Linha " + line + ": comentario nao fechado");
                } else {
                    String simbolo = msg.substring(msg.indexOf("'") + 1, msg.lastIndexOf("'"));
                    writer.println("Linha " + line + ": " + simbolo + " - simbolo nao identificado");
                }
            } else {
                // Tratamento para ERROS SINTÁTICOS
                Token t = (Token) offendingSymbol;
                String texto = t.getText();
                if (texto.equals("<EOF>")) {
                    texto = "EOF";
                }
                writer.println("Linha " + line + ": erro sintatico proximo a " + texto);
            }

            jaImprimiuErro = true;
        }
    }
}