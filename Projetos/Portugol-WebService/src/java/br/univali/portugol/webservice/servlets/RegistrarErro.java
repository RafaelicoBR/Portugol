package br.univali.portugol.webservice.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.univali.portugol.webservice.WebService;

/**
 * 
 * @author Luiz Fernando Noschang
 * 
 */
public final class RegistrarErro extends HttpServlet
{
    private static final long serialVersionUID = 7177031352917253056L;
    private static String sql = null;
    private static final SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    @Override
    public void init(ServletConfig configuracao) throws ServletException
    {
        carregarSql(configuracao);
    }

    @Override
    protected void doPost(HttpServletRequest requisicao, HttpServletResponse resposta) throws ServletException, IOException
    {
        Connection conexao = null;
        PreparedStatement declaracao = null;

        try
        {
            conexao = WebService.getInstancia().getPersistencia().getConexao();
            declaracao = conexao.prepareStatement(sql);

            declaracao.setString(1, requisicao.getParameter("erro.aplicativo"));
            declaracao.setString(2, requisicao.getParameter("erro.versao"));
            declaracao.setString(3, requisicao.getParameter("erro.mensagem"));
            declaracao.setString(4, requisicao.getParameter("erro.classe"));
            declaracao.setString(5, requisicao.getParameter("erro.stacktrace"));
            declaracao.setString(6, requisicao.getParameter("erro.algoritmo"));
            declaracao.setTimestamp(7, new Timestamp(formatadorData.parse(requisicao.getParameter("erro.data")).getTime()));
            declaracao.setString(8, requisicao.getParameter("erro.informacoesSO"));
            declaracao.setString(9, requisicao.getParameter("erro.informacoesJVM"));
            declaracao.setString(10, requisicao.getParameter("erro.macAddress"));

            declaracao.execute();
            conexao.commit();
        }
        catch (Exception e)
        {
            try
            {
                conexao.rollback();
            }
            catch (Exception e2)
            {
            }
        }
        finally
        {
            try
            {
                declaracao.close();
            }
            catch (Exception e)
            {
            }
            try
            {
                conexao.close();
            }
            catch (Exception e)
            {
            }
        }
    }

    private void carregarSql(ServletConfig configuracao)
    {
        InputStream fluxoEntrada = null;
        BufferedReader leitorTexto = null;
        InputStreamReader leitorFluxo = null;
        StringBuilder construtorTexto = null;
        String linha = null;

        try
        {
            fluxoEntrada = getClass().getResourceAsStream("/br/univali/portugol/webservice/servlets/RegistrarErro.sql");
            leitorFluxo = new InputStreamReader(fluxoEntrada);
            leitorTexto = new BufferedReader(leitorFluxo);
            construtorTexto = new StringBuilder();

            while ((linha = leitorTexto.readLine()) != null)
            {
                construtorTexto.append(linha);
            }

            sql = construtorTexto.toString();

        }
        catch (Exception e)
        {
        }
        finally
        {
            try
            {
                leitorTexto.close();
            }
            catch (Exception e)
            {
            }
            try
            {
                leitorFluxo.close();
            }
            catch (Exception e)
            {
            }
            try
            {
                fluxoEntrada.close();
            }
            catch (Exception e)
            {
            }
        }
    }
}
