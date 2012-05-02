package br.univali.portugol.webservice;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 
 * @author Luiz Fernando Noschang
 * 
 */
public final class Inicializador implements ServletContextListener
{
    @Override
    public void contextInitialized(ServletContextEvent eventoInicializacao)
    {
        inicializarPersistencia(eventoInicializacao.getServletContext());
        inicializarAutenticacao(eventoInicializacao.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent eventoFinalizacao)
    {
    }

    private void inicializarPersistencia(ServletContext contexto)
    {
        try
        {
            Properties propriedades = new Properties();
            propriedades.load(contexto.getResourceAsStream("/WEB-INF/persistencia.properties"));

            WebService.getInstancia().getPersistencia().inicializar(propriedades);

        }
        catch (Exception excecao)
        {
            excecao.printStackTrace(System.out);
        }
    }

    private void inicializarAutenticacao(ServletContext contexto)
    {
        try
        {
            Properties propriedades = new Properties();
            propriedades.load(contexto.getResourceAsStream("/WEB-INF/autenticacao.properties"));

            WebService.getInstancia().getAutenticacao().inicializar(propriedades);
        }
        catch (Exception excecao)
        {
            excecao.printStackTrace(System.out);
        }
    }
}
