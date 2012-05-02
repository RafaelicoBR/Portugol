package br.univali.portugol.integracao.servico;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Luiz Fernando Noschang
 * 
 */
public final class ThreadProcessoOrbd extends Thread
{
    private Properties configuracoes;
    private Process processoOrbd;

    public ThreadProcessoOrbd(Properties configuracoes)
    {
        super("Processo orbd");
        this.configuracoes = configuracoes;
    }

    @Override
    public void run()
    {
        try
        {
            List<String> comandos = new ArrayList<String>();
            comandos.add("orbd");

            for (String properiedade : configuracoes.stringPropertyNames())
            {
                comandos.add(properiedade);
                comandos.add(configuracoes.getProperty(properiedade));
            }

            ProcessBuilder construtorProcesso = new ProcessBuilder(comandos);
            processoOrbd = construtorProcesso.start();

            MonitorErrosProcesso monitorErrosProcesso = new MonitorErrosProcesso(processoOrbd);
            monitorErrosProcesso.start();

            processoOrbd.waitFor();
        }
        catch (Exception excecao)
        {
            System.err.println("Ocorreu um erro ao tentar iniciar o processo orbd, necessário para o funcionamento do serviço de integração do Portugol:");
            System.err.println();

            excecao.printStackTrace(System.err);

            System.err.println();
            System.err.println("O serviço de integração do Portugol será finalizado...");

            System.exit(1);
        }
    }
    
    public void encerrar()
    {
        processoOrbd.destroy();
    }

    private final class MonitorErrosProcesso extends Thread
    {
        private Process processo;

        public MonitorErrosProcesso(Process processo)
        {
            this.processo = processo;
        }

        @Override
        public void run()
        {
            BufferedReader bufferedReader = null;
            InputStreamReader inputStreamReader = null;
            String linha = null;

            try
            {
                inputStreamReader = new InputStreamReader(processo.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);

                while (!bufferedReader.ready()) { Thread.sleep(1000); }

                System.err.println("Ocorreu um erro ao tentar iniciar o processo orbd, necess�rio para o funcionamento do servi�o de integra��o do Portugol:");
                System.err.println();

                while ((linha = bufferedReader.readLine()) != null)
                {
                    System.err.println(linha);
                }

                System.err.println();
                System.err.println("O servi�o de integra��o do Portugol ser� finalizado...");
                
                System.exit(1);
            }
            catch (Exception exception) 
            {
                exception.printStackTrace(System.err);
                System.exit(1);
            }
            finally
            {
                try { bufferedReader.close(); } catch (Exception exception) { }
                try { inputStreamReader.close(); } catch (Exception exception) { }
            }
        }
    }
}
