package br.ufscar.dc.compiladores.receita;

import java.util.HashMap;
import java.util.Collection;
import org.antlr.v4.runtime.Token;

public class TabelaDeSimbolos {
    private HashMap<String, DefinicaoIngrediente> tabela;

    public TabelaDeSimbolos() {
        this.tabela = new HashMap<>();
    }

    public void adicionar(String nome, Token token) {
        tabela.put(nome, new DefinicaoIngrediente(nome, token));
    }

    public boolean existe(String nome) {
        return tabela.containsKey(nome);
    }

    public DefinicaoIngrediente obter(String nome){
        return tabela.get(nome);
    }

    public Collection<DefinicaoIngrediente> obterTodos(){
        return tabela.values();
    }
}