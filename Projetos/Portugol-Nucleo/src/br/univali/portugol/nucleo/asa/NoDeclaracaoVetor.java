package br.univali.portugol.nucleo.asa;

/**
 * 
 * @author Luiz Fernando Noschang
 * 
 */
public final class NoDeclaracaoVetor extends NoDeclaracao
{
    private NoExpressao tamanho;

    public NoDeclaracaoVetor(String nome, TipoDado tipoDado, NoExpressao tamanho, boolean constante)
    {
        super(nome, tipoDado, constante);
        this.tamanho = tamanho;
    }

    public NoExpressao getTamanho()
    {
        return tamanho;
    }

    @Override
    public Object aceitar(VisitanteASA visitante) throws ExcecaoVisitaASA
    {
        return visitante.visitar(this);
    }
}
