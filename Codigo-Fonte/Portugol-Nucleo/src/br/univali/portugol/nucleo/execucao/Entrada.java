package br.univali.portugol.nucleo.execucao;

import br.univali.portugol.nucleo.asa.TipoDado;

/**
 * 
 * @author Luiz Fernando Noschang
 * 
 */

public interface Entrada
{
	public Object ler(TipoDado tipoDado) throws Exception;
}
