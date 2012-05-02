package br.univali.portugol.integracao.servico;

/**
 *
 * @author Luiz Fernando Noschang
 * 
 */

final class Inicializador
{
    public static void main(String[] argumentos)
    {
        ServicoIntegracao servicoIntegracao = ServicoIntegracao.getInsance();
        servicoIntegracao.iniciar();
    }
}
