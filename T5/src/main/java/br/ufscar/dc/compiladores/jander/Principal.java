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
        // Validação das entradas: exige arquivo fonte (.alg) e arquivo de destino (.c ou .txt)
        if (args.length < 2) {
            System.err.println("Erro: São necessários dois argumentos (entrada e saída).");
            return;
        }

        String arquivoEntrada = args[0];
        String arquivoSaida = args[1];

        // Utiliza PrintWriter para garantir que a saída seja escrita no arquivo, e não no terminal
        try (PrintWriter pw = new PrintWriter(new FileWriter(arquivoSaida))) {
            try {
                // Etapa 1: Leitura do arquivo de entrada e Análise Léxica
                CharStream cs = CharStreams.fromFileName(arquivoEntrada);
                JanderLexer lexer = new JanderLexer(cs);
                CommonTokenStream tokens = new CommonTokenStream(lexer);

                // Etapa 2: Análise Sintática construindo a Árvore Sintática Abstrata (AST)
                JanderParser parser = new JanderParser(tokens);
                JanderParser.ProgramaContext arvore = parser.programa();

                // Etapa 3: Análise Semântica percorrendo a AST
                JanderSemantico semantico = new JanderSemantico();
                semantico.visitPrograma(arvore);

                // Verificação de erros semânticos
                if (!JanderSemanticoUtils.errosSemanticos.isEmpty()) {
                    // Se existirem erros, imprime a lista no arquivo e aborta a geração de código
                    JanderSemanticoUtils.errosSemanticos.forEach(pw::println);
                    pw.println("Fim da compilacao");
                } else {
                    // Etapa 4: Geração de Código C
                    // Como não há erros, instancia o gerador e percorre a AST novamente
                    Gerador gerador = new Gerador();
                    gerador.visitPrograma(arvore);

                    // Salva o código C traduzido no arquivo de saída
                    pw.print(gerador.saida.toString());
                }

            } catch (Exception e) {
                System.err.println("Erro interno: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Erro ao manipular arquivos: " + e.getMessage());
        }
    }
}