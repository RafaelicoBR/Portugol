package br.univali.portugol.integracao.execucao;

import br.univali.portugol.integracao.mensagens.ErroExecucao;
import org.omg.CORBA.UNKNOWN;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

/**
 *
 * @author Luiz Fernando Noschang
 * 
 */
public final class ResultadoExecucaoWrapper extends ResultadoExecucaoPOA
{
    private br.univali.portugol.nucleo.execucao.ResultadoExecucao resultadoExecucao;

    public static ResultadoExecucao empacotar(br.univali.portugol.nucleo.execucao.ResultadoExecucao resultadoExecucaoPortugol, POA rootPOA)
    {
        try
        {
            ResultadoExecucaoWrapper resultadoExecucaoWrapper = new ResultadoExecucaoWrapper(resultadoExecucaoPortugol);
            org.omg.CORBA.Object referencia = rootPOA.servant_to_reference(resultadoExecucaoWrapper);
            ResultadoExecucao programaCORBA = ResultadoExecucaoHelper.narrow(referencia);

            return programaCORBA;
        }
        catch (WrongPolicy excecao)
        {
            throw new UNKNOWN(excecao.getMessage());
        }
        catch (ServantNotActive excecao)
        {
            throw new UNKNOWN(excecao.getMessage());
        }
    }

    private ResultadoExecucaoWrapper(br.univali.portugol.nucleo.execucao.ResultadoExecucao resultadoExecucao)
    {
        this.resultadoExecucao = resultadoExecucao;
    }

    @Override
    public long getTempoExecucao()
    {
        return this.resultadoExecucao.getTempoExecucao();
    }

    @Override
    public ModoEncerramento getModoEncerramento()
    {
        return br.univali.portugol.integracao.execucao.ModoEncerramento.from_int(this.resultadoExecucao.getModoEncerramento().ordinal());
    }

    @Override
    public ErroExecucao getErro()
    {
        return this.empacotar(this.resultadoExecucao.getErro());
    }

    private ErroExecucao empacotar(br.univali.portugol.nucleo.mensagens.ErroExecucao erroExecucaoPortugol)
    {
        return new ErroExecucao(erroExecucaoPortugol.getMensagem(), erroExecucaoPortugol.getMensagem());
    }
}
