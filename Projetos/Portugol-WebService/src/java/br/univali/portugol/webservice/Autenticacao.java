package br.univali.portugol.webservice;

import java.util.Properties;

/**
 * 
 * @author Luiz Fernando Noschang
 * 
 */
public final class Autenticacao
{
    private Properties propriedades;

    public void inicializar(Properties propriedades)
    {
        this.propriedades = propriedades;
    }

    public boolean autenticar(String usuario, String senha)
    {
        if ((usuario != null) && (senha != null))
        {
            boolean usuarioCorreto = usuario.equals(propriedades.getProperty("autenticacao.usuario"));
            boolean senhaCorreta = senha.equals(propriedades.getProperty("autenticacao.senha"));

            return (usuarioCorreto && senhaCorreta);
        }

        return false;
    }
}
