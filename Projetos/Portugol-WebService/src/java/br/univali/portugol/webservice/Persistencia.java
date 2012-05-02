package br.univali.portugol.webservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * @author Luiz Fernando Noschang
 * 
 */
public final class Persistencia
{
    private Properties propriedades;

    public void inicializar(Properties propriedades) throws Exception
    {
        this.propriedades = propriedades;
        this.propriedades.put("user", this.propriedades.getProperty("persistencia.usuario"));
        this.propriedades.put("password", this.propriedades.getProperty("persistencia.senha"));

        Class.forName(propriedades.getProperty("persistencia.driver"));
    }

    public Connection getConexao() throws SQLException
    {
        Connection conexao = DriverManager.getConnection(propriedades.getProperty("persistencia.url"), propriedades);
        conexao.setAutoCommit(false);

        return conexao;
    }
}
