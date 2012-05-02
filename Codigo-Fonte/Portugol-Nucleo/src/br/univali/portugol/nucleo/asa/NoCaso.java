package br.univali.portugol.nucleo.asa;

import java.util.List;

/**
 * 
 * @author Luiz Fernando Noschang
 * 
 */
public final class NoCaso extends NoBloco
{
    private List<NoBloco> blocos;
    private NoExpressao expressao;

    public NoCaso(NoExpressao expressao)
    {
        this.expressao = expressao;
    }

    public List<NoBloco> getBlocos()
    {
        return blocos;
    }

    public NoExpressao getExpressao()
    {
        return expressao;
    }

    public void setBlocos(List<NoBloco> blocos)
    {
        this.blocos = blocos;
    }

    @Override
    public Object aceitar(VisitanteASA visitante) throws ExcecaoVisitaASA
    {
        return visitante.visitar(this);
    }
}