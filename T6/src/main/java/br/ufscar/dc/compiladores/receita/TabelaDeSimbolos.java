package br.ufscar.dc.compiladores.receita;

import java.util.HashMap;

public class TabelaDeSimbolos {
    // Armazena o nome do ingrediente e o tipo
    private HashMap<String, String> tabela;

    public TabelaDeSimbolos() {
        this.tabela = new HashMap<>();
    }

    public void adicionar(String nome) {
        tabela.put(nome, "INGREDIENTE");
    }

    public boolean existe(String nome) {
        return tabela.containsKey(nome);
    }
}