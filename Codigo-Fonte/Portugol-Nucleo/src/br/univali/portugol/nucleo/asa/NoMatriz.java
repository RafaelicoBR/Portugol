package br.univali.portugol.nucleo.asa;

import java.util.List;

/**
 * 
 * @author Luiz Fernando Noschang
 * 
 */
public final class NoMatriz extends NoExpressao
{
    private List<List<Object>> valores;

    public NoMatriz(List<List<Object>> valores)
    {
        this.valores = valores;
    }

    public List<List<Object>> getValores()
    {
        return valores;
    }

    @Override
    protected TrechoCodigoFonte montarTrechoCodigoFonte()
    {
        return null;
    }

    @Override
    public Object aceitar(VisitanteASA visitante) throws ExcecaoVisitaASA
    {
        return visitante.visitar(this);
    }
}
