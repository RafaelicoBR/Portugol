package br.univali.portugol.webservice.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import br.univali.portugol.webservice.WebService;

/**
 * 
 * @author Luiz Fernando Noschang
 * 
 */
public final class FiltroAutenticacao implements Filter
{
    @Override
    public void doFilter(ServletRequest requisicao, ServletResponse resposta, FilterChain corrente) throws IOException, ServletException
    {
        String usuario = requisicao.getParameter("autenticacao.usuario");
        String senha = requisicao.getParameter("autenticacao.senha");

        if (WebService.getInstancia().getAutenticacao().autenticar(usuario, senha))
        {
            corrente.doFilter(requisicao, resposta);
        }
        else
        {
            HttpServletResponse respostaHttp = (HttpServletResponse) resposta;
            respostaHttp.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @Override
    public void init(FilterConfig configuracao) throws ServletException
    {
    }

    @Override
    public void destroy()
    {
    }
}
