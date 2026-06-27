package  br.ufscar.dc.compiladores.receita;

import java.util.HashMap;
import org.antlr.v4.runtime.Token;

public class DefinicaoIngrediente {
    private String nome;
    private Token declaracao;
    private HashMap<String, String> substituicoes;
    private boolean utilizado;

    public DefinicaoIngrediente(String nome, Token declaracao){
        this.nome = nome;
        this.declaracao = declaracao;
        this.substituicoes = new HashMap<>();
        this.utilizado = false; // inicialmente, todo ingrediente não foi utilizado
    }

    public void adicionarSubstituicao(String restricao, String substituto){
        this.substituicoes.put(restricao.toLowerCase(), substituto);
    }

    public String getSubstituto(String restricao){
        return this.substituicoes.get(restricao.toLowerCase());
    }

    public void marcarComoUtilizado(){
        this.utilizado = true;
    }

    public boolean isUtilizado(){
        return this.utilizado;
    }

    public Token getTokenDeclaracao(){
        return this.declaracao;
    }
}

