package br.univali.portugol.integracao;

import br.univali.portugol.integracao.analise.ResultadoAnalise;
import br.univali.portugol.integracao.analise.ResultadoAnaliseWrapper;
import br.univali.portugol.integracao.servico.ThreadObjectRequestBroker;
import br.univali.portugol.integracao.servico.ThreadProcessoOrbd;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

/**
 *
 * @author Luiz Fernando Noschang
 * 
 */
public final class PortugolWrapper extends PortugolPOA
{
    private POA rootPOA;
    private ThreadProcessoOrbd threadProcessoOrbd;
    private ThreadObjectRequestBroker threadObjectRequestBroker;
    
    private PortugolWrapper(ThreadProcessoOrbd threadProcessoOrbd, ThreadObjectRequestBroker threadObjectRequestBroker, POA rootPOA)
    {
        this.rootPOA = rootPOA;
        this.threadProcessoOrbd = threadProcessoOrbd;
        this.threadObjectRequestBroker = threadObjectRequestBroker;
    }

    public static Portugol getPortugol(ThreadProcessoOrbd threadProcessoOrbd, ThreadObjectRequestBroker threadObjectRequestBroker, POA rootPOA) throws ServantNotActive, WrongPolicy
    {
        PortugolWrapper portugolWrapper = new PortugolWrapper(threadProcessoOrbd, threadObjectRequestBroker, rootPOA);
        org.omg.CORBA.Object referenciaPortugol = rootPOA.servant_to_reference(portugolWrapper);
        
        return PortugolHelper.narrow(referenciaPortugol);
    }
    
    @Override
    public ResultadoAnalise analisar(String codigo)
    {
        br.univali.portugol.nucleo.analise.ResultadoAnalise resultadoAnalisePortugol = br.univali.portugol.nucleo.Portugol.analisar(codigo);
        ResultadoAnalise resultadoAnaliseCORBA = ResultadoAnaliseWrapper.empacotar(resultadoAnalisePortugol, rootPOA);

        return resultadoAnaliseCORBA;
    }

    @Override
    public Programa compilar(String codigo) throws ErroCompilacao
    {
        try
        {
            br.univali.portugol.nucleo.Programa programaPortugol = br.univali.portugol.nucleo.Portugol.compilar(codigo);
            Programa programa = ProgramaWrapper.empacotar(programaPortugol, rootPOA);
            
            return programa;
        }
        catch (br.univali.portugol.nucleo.ErroCompilacao erroCompilacaoPortugol)
        {
            ErroCompilacao erroCompilacaoCORBA = empacotar(erroCompilacaoPortugol);
            
            throw erroCompilacaoCORBA;
        }
    }

    private ErroCompilacao empacotar(br.univali.portugol.nucleo.ErroCompilacao erroCompilacaoPortugol)
    {
        br.univali.portugol.nucleo.analise.ResultadoAnalise resultadoAnalisePortugol = erroCompilacaoPortugol.getResultadoAnalise();
        ResultadoAnalise resultadoAnaliseCORBA = ResultadoAnaliseWrapper.empacotar(resultadoAnalisePortugol, rootPOA);

        return new ErroCompilacao(erroCompilacaoPortugol.getMensagem(), resultadoAnaliseCORBA);
    }

    @Override
    public void encerrar()
    {
        new Thread(new Runnable() 
        {
            @Override
            public void run()
            {
                try { Thread.sleep(2000); } catch (Exception excecao) {}
                
                System.out.println("Finalizando o Object Request Broker (ORB) para Java...");

                threadObjectRequestBroker.encerrar();

                System.out.println("O Object Request Broker (ORB) para Java foi finalizado corretamente");
                System.out.println();
                System.out.println("Finalizando o processo orbd...");

                threadProcessoOrbd.encerrar();

                System.out.println("O processo orbd foi finalizado correamente.");
                System.out.println();
                System.out.println("Finalizando o serviço de integração do Portugol...");

                System.exit(0);
            }
        }).start();
    }
}
