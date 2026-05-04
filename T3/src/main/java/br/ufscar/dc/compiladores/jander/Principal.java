package br.ufscar.dc.compiladores.jander;

import br.ufscar.dc.compiladores.jander.JanderParser.ProgramaContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Principal {

    public static void main(String args[]) {
        if (args.length < 2) {
            System.err.println("Erro: São necessários dois argumentos (entrada e saída).");
            return;
        }

        String arquivoEntrada = args[0];
        String arquivoSaida = args[1];

        try (PrintWriter pw = new PrintWriter(new FileWriter(arquivoSaida))) {
            try {
                // Leitura do código-fonte
                CharStream cs = CharStreams.fromFileName(arquivoEntrada);

                JanderLexer lexer = new JanderLexer(cs);
                CommonTokenStream tokens = new CommonTokenStream(lexer);

                // Análise Sintática
                JanderParser parser = new JanderParser(tokens);
                JanderParser.ProgramaContext arvore = parser.programa();

                // Análise Semântica
                JanderSemantico semantico = new JanderSemantico();
                semantico.visitPrograma(arvore);

                // Impressão dos erros acumulados
                JanderSemanticoUtils.errosSemanticos.forEach(pw::println);

                // Mensagem final obrigatória
                pw.println("Fim da compilacao");

            } catch (Exception e) {
                System.err.println("Erro interno: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Erro ao manipular arquivos: " + e.getMessage());
        }
    }
}