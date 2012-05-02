package br.univali.portugol.integracao.servico;

import java.util.Properties;

/**
 *
 * @author Luiz Fernando Noschang
 * 
 */
final class ServicoIntegracao
{
    private static ServicoIntegracao instance = null;
    private static final int PORTA_PADRAO = 53787;
    private static final int TEMPO_ESEPERA = 2500;
    
    private ThreadProcessoOrbd threadProcessoOrbd;
    private ThreadObjectRequestBroker threadObjectRequestBroker;

    private ServicoIntegracao()
    {
        
    }

    public static ServicoIntegracao getInsance()
    {
        if (instance == null)
            instance = new ServicoIntegracao();

        return instance;
    } 

    public void iniciar()
    {
        Properties configuracoes = carregarConfiguracoes();
        
        System.out.println("Iniciando o serviço de integração do Portugol:");
        System.out.println();
        
        System.out.println("Iniciando o processo orbd...");
        
        threadProcessoOrbd = new ThreadProcessoOrbd(configuracoes);
        threadProcessoOrbd.start();
        
        try { Thread.sleep(TEMPO_ESEPERA); } catch (Exception excecao) {}
        
        System.out.println("O processo orbd foi iniciado corretamente");
        System.out.println();
        System.out.println("Iniciando o Object Request Broker (ORB) para Java...");
        
        threadObjectRequestBroker = new ThreadObjectRequestBroker(configuracoes, threadProcessoOrbd);
        threadObjectRequestBroker.start();
        
        try { Thread.sleep(TEMPO_ESEPERA); } catch (Exception excecao) {}
        
        System.out.println("O Object Request Broker para (ORB) Java foi iniciado corretamente");
        System.out.println();
        System.out.println("O serviçoo de integração do Portugol está ativo, aguardando requisições...");
    }    
    
    private Properties carregarConfiguracoes()
    {
        Properties configuracoes = new Properties();
        
        configuracoes.setProperty("-ORBInitialHost", "localhost");
        configuracoes.setProperty("-ORBInitialPort", Integer.toString(PORTA_PADRAO));
        configuracoes.setProperty("-defaultdb", String.format("%s%s%s", System.getProperty("user.home"), System.getProperty("file.separator"), "portugol-integracao"));
        
        return configuracoes;
    }
}
