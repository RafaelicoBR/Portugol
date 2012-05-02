package br.univali.portugol.webservice;

/**
 * 
 * @author Luiz Fernando Noschang
 * 
 */
public final class WebService
{
    private static WebService instancia = null;
    private Persistencia persistencia = null;
    private Autenticacao autenticacao = null;

    private WebService()
    {
    }

    public static WebService getInstancia()
    {
        if (instancia == null)
        {
            instancia = new WebService();
        }

        return instancia;
    }

    public Persistencia getPersistencia()
    {
        if (persistencia == null)
        {
            persistencia = new Persistencia();
        }

        return persistencia;
    }

    public Autenticacao getAutenticacao()
    {
        if (autenticacao == null)
        {
            autenticacao = new Autenticacao();
        }

        return autenticacao;
    }
}
