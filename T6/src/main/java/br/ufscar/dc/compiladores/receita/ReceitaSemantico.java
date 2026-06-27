package br.ufscar.dc.compiladores.receita;

public class ReceitaSemantico extends ReceitaBaseVisitor<Void> {

    TabelaDeSimbolos tabela = new TabelaDeSimbolos();

    @Override
    public Void visitReceita(ReceitaParser.ReceitaContext ctx) {
        super.visitReceita(ctx);

        // depois que a árvore toda foi lida, varre-se a tabela
        for (DefinicaoIngrediente simbolo : tabela.obterTodos()) {
            if (!simbolo.isUtilizado()) {
                ReceitaSemanticoUtils.adicionarErroSemantico(
                        simbolo.getTokenDeclaracao(),
                        "ingrediente " + simbolo.getTokenDeclaracao().getText() + " foi declarado, mas nunca utilizado nos passos"
                );
            }
        }

        return null;
    }

    // Verifica se a quantidade de porções é válida
    @Override
    public Void visitCabecalho(ReceitaParser.CabecalhoContext ctx) {
        int porcoes = Integer.parseInt(ctx.NUMERO().getText());
        if (porcoes <= 0) {
            ReceitaSemanticoUtils.adicionarErroSemantico(
                    ctx.NUMERO().getSymbol(),
                    "quantidade de porcoes deve ser maior que zero"
            );
        }
        return super.visitCabecalho(ctx);
    }

    // Verifica ingredientes duplicados
    @Override
    public Void visitIngrediente(ReceitaParser.IngredienteContext ctx) {
        String nomeIngrediente = ctx.STRING().getText();

        if (tabela.existe(nomeIngrediente)) {
            ReceitaSemanticoUtils.adicionarErroSemantico(
                    ctx.STRING().getSymbol(),
                    "ingrediente " + nomeIngrediente + " ja declarado"
            );
        } else {
            tabela.adicionar(nomeIngrediente, ctx.STRING().getSymbol());

            // verifica se existe a regra de substituicao
            if (ctx.substituicao() != null){
                // recupera a restrição (ex: vegano) e o novo ingrediente (ex: linhaça)
                String restricao = ctx.substituicao().restricao().getText();
                String substituto = ctx.substituicao().STRING().getText();

                DefinicaoIngrediente simbolo = tabela.obter(nomeIngrediente);
                simbolo.adicionarSubstituicao(restricao, substituto);
            }
        }

        return super.visitIngrediente(ctx);
    }

    // Verifica se o ingrediente usado no passo foi declarado
    @Override
    public Void visitPasso(ReceitaParser.PassoContext ctx) {
        if (ctx.argumentos() != null) {
            for (ReceitaParser.ArgumentoContext argCtx : ctx.argumentos().argumento()) {
                // Se o argumento for string, significa que é o nome de um ingrediente
                if (argCtx.STRING() != null) {
                    String nomeIngrediente = argCtx.STRING().getText();
                    if (!tabela.existe(nomeIngrediente)) {
                        ReceitaSemanticoUtils.adicionarErroSemantico(
                                argCtx.STRING().getSymbol(),
                                "ingrediente " + nomeIngrediente + " nao declarado"
                        );
                    } else {
                        // marca como utilizado o ingrediente
                        DefinicaoIngrediente ingrediente = tabela.obter(nomeIngrediente);
                        ingrediente.marcarComoUtilizado();
                    }
                }
            }
        }
        return super.visitPasso(ctx);
    }
}