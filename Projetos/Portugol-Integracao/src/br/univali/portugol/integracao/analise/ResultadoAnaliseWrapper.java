package br.univali.portugol.integracao.analise;

import br.univali.portugol.integracao.mensagens.AvisoAnalise;
import br.univali.portugol.integracao.mensagens.ErroSemantico;
import br.univali.portugol.integracao.mensagens.ErroSintatico;
import java.util.List;
import org.omg.CORBA.UNKNOWN;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

/**
 *
 * @author Luiz Fernando Noschang
 * 
 */
public final class ResultadoAnaliseWrapper extends ResultadoAnalisePOA
{
    private br.univali.portugol.nucleo.analise.ResultadoAnalise resultadoAnalise;

    public static ResultadoAnalise empacotar(br.univali.portugol.nucleo.analise.ResultadoAnalise resultadoAnalisePortugol, POA rootPOA)
    {
        try
        {
            ResultadoAnaliseWrapper resultadoAnaliseWrapper = new ResultadoAnaliseWrapper(resultadoAnalisePortugol);
            org.omg.CORBA.Object referencia = rootPOA.servant_to_reference(resultadoAnaliseWrapper);
            ResultadoAnalise resultadoAnaliseCORBA = ResultadoAnaliseHelper.narrow(referencia);

            return resultadoAnaliseCORBA;
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
    
    private ResultadoAnaliseWrapper(br.univali.portugol.nucleo.analise.ResultadoAnalise resultadoAnalise)
    {
        this.resultadoAnalise = resultadoAnalise;
    }

    @Override
    public int getNumeroTotalErros()
    {
        return this.resultadoAnalise.getNumeroTotalErros();
    }

    @Override
    public int getNumeroErrosSintaticos()
    {
        return this.resultadoAnalise.getNumeroErrosSintaticos();
    }

    @Override
    public int getNumeroErrosSemanticos()
    {
        return this.resultadoAnalise.getNumeroErrosSemanticos();
    }

    @Override
    public int getNumeroAvisos()
    {
        return this.resultadoAnalise.getNumeroAvisos();
    }

    @Override
    public AvisoAnalise[] getAvisos()
    {
        List<br.univali.portugol.nucleo.mensagens.AvisoAnalise> avisosAnalisePortugol = this.resultadoAnalise.getAvisos();
        AvisoAnalise[] avisosCORBA = new AvisoAnalise[avisosAnalisePortugol.size()];

        for (int i = 0; i < avisosAnalisePortugol.size(); i++)
        {
            avisosCORBA[i] = empacotar(avisosAnalisePortugol.get(i));
        }

        return avisosCORBA;
    }

    @Override
    public ErroSintatico[] getErrosSintaticos()
    {
        List<br.univali.portugol.nucleo.mensagens.ErroSintatico> erroSintaticosPortugol = this.resultadoAnalise.getErrosSintaticos();
        ErroSintatico[] errosSintaticosCORBA = new ErroSintatico[erroSintaticosPortugol.size()];

        for (int i = 0; i < erroSintaticosPortugol.size(); i++)
        {
            errosSintaticosCORBA[i] = empacotar(erroSintaticosPortugol.get(i));
        }

        return errosSintaticosCORBA;
    }

    @Override
    public ErroSemantico[] getErrosSemanticos()
    {
        List<br.univali.portugol.nucleo.mensagens.ErroSemantico> erroSemanticosPortugol = this.resultadoAnalise.getErrosSemanticos();
        ErroSemantico[] errosSemanticosCORBA = new ErroSemantico[erroSemanticosPortugol.size()];

        for (int i = 0; i < erroSemanticosPortugol.size(); i++)
        {
            errosSemanticosCORBA[i] = empacotar(erroSemanticosPortugol.get(i));
        }

        return errosSemanticosCORBA;
    }

    private ErroSintatico empacotar(br.univali.portugol.nucleo.mensagens.ErroSintatico erroSintaticoPortugol)
    {
        String mensagem = erroSintaticoPortugol.getMensagem();
        int linha = erroSintaticoPortugol.getLinha();
        int coluna = erroSintaticoPortugol.getColuna();

        return new ErroSintatico(mensagem, mensagem, linha, coluna);
    }

    private ErroSemantico empacotar(br.univali.portugol.nucleo.mensagens.ErroSemantico erroSemanticoPortugol)
    {

        String mensagem = erroSemanticoPortugol.getMensagem();
        int linha = erroSemanticoPortugol.getLinha();
        int coluna = erroSemanticoPortugol.getColuna();

        return new ErroSemantico(mensagem, mensagem, linha, coluna);
    }

    private AvisoAnalise empacotar(br.univali.portugol.nucleo.mensagens.AvisoAnalise avisoAnalisePortugol)
    {

        String mensagem = avisoAnalisePortugol.getMensagem();
        int linha = avisoAnalisePortugol.getLinha();
        int coluna = avisoAnalisePortugol.getColuna();

        return new AvisoAnalise(mensagem, mensagem, linha, coluna);
    }
}
