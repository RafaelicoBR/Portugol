package br.univali.portugol.integracao.servico;

import br.univali.portugol.integracao.Portugol;
import br.univali.portugol.integracao.PortugolWrapper;
import java.util.Properties;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

/**
 *
 * @author Luiz Fernando Noschang
 * 
 */
public final class ThreadObjectRequestBroker extends Thread
{
    private Properties configuracoes;
    private ThreadProcessoOrbd threadProcessoOrbd;
    
    private ORB orb;
    private POA rootPOA;

    public ThreadObjectRequestBroker(Properties configuracoes, ThreadProcessoOrbd threadProcessoOrbd)
    {
        this.configuracoes = configuracoes;
        this.threadProcessoOrbd = threadProcessoOrbd;
    }
    
    @Override
    public void run()
    {
        try
        {
            orb = ORB.init(converterConfiguracoes(), null);

            rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();

            Portugol portugol = PortugolWrapper.getPortugol(threadProcessoOrbd, this, rootPOA);

            org.omg.CORBA.Object referenciaServicoNomes = orb.resolve_initial_references("NameService");
            NamingContextExt servicoNomes = NamingContextExtHelper.narrow(referenciaServicoNomes);
            
            String nome = "Portugol";
            NameComponent caminho[] = servicoNomes.to_name(nome);
            servicoNomes.rebind(caminho, portugol);

            orb.run();
        }
        catch (Exception excecao)
        {
            System.err.println("Ocorreu um erro ao tentar iniciar o Object Request Broker (ORB) para Java, necessário para o funcionamento do serviço de integração do Portugol:");
            System.err.println();

            excecao.printStackTrace(System.err);

            System.err.println();
            System.err.println("O serviço de integração do Portugol será finalizado...");

            threadProcessoOrbd.encerrar();            
            System.exit(1);
        }
    }    
    
    private String[] converterConfiguracoes()
    {
        int indice = 0;
        String[] argumentos = new String[configuracoes.size() * 2];
        
        for (String propriedade : configuracoes.stringPropertyNames())
        {
            argumentos[indice] = propriedade;
            argumentos[indice + 1] = configuracoes.getProperty(propriedade);
            
            indice = indice + 2;
        }
        
        return argumentos;
    }
    
    public void encerrar()
    {
        orb.shutdown(true);
        orb.destroy();        
    }
}
