package br.ufscar.dc.compiladores.jander;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class TabelaDeSimbolos {

    public enum Categoria {
        VARIAVEL, CONSTANTE, PROCEDIMENTO, FUNCAO, TIPO
    }

    public static class EntradaTabelaDeSimbolos {
        public String nome;
        public Categoria categoria;
        public String tipo; // Ex: "inteiro", "^real", "registro"
        public List<String> parametros = new ArrayList<>(); // Usado para funcoes/procedimentos
        public TabelaDeSimbolos campos = null; // Armazena propriedades de registros

        public EntradaTabelaDeSimbolos(String nome, Categoria categoria, String tipo) {
            this.nome = nome;
            this.categoria = categoria;
            this.tipo = tipo;
        }
    }

    private final Map<String, EntradaTabelaDeSimbolos> tabela = new HashMap<>();

    public void adicionar(String nome, Categoria categoria, String tipo) {
        tabela.put(nome, new EntradaTabelaDeSimbolos(nome, categoria, tipo));
    }

    public boolean existe(String nome) {
        return tabela.containsKey(nome);
    }

    // O método verificar retorna a entrada completa (com categoria, tipo em String e subcampos)
    public EntradaTabelaDeSimbolos verificar(String nome) {
        return tabela.get(nome);
    }
}