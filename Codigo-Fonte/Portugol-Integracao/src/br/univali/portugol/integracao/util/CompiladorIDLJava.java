package br.univali.portugol.integracao.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Luiz Fernando Noschang
 * 
 */
final class CompiladorIDLJava
{
    public static void main(String[] argumentos) throws IOException
    {
        CompiladorIDLJava compilador = new CompiladorIDLJava();
        compilador.compilar();
    }
    
    private void compilar() throws IOException
    {
        File arquivoIDL = new File("./src/br/univali/portugol/integracao/Portugol.idl");
        File diretorioSaida = new File("./src-idl-generated");

        try
        {
            ProcessBuilder inicializadorProcesso = new ProcessBuilder("idlj", "-fall", "-td", diretorioSaida.getCanonicalPath(), arquivoIDL.getCanonicalPath());
            inicializadorProcesso.redirectErrorStream(false);

            Process processoIdlj = inicializadorProcesso.start();
            MonitorSaidaProcesso monitorSaidaProcesso = new MonitorSaidaProcesso(processoIdlj);

            monitorSaidaProcesso.start();
            processoIdlj.waitFor();
            
            System.out.println("As interfaces do arquivo Portugol.idl foram copiladas corretamente");
            System.exit(0);
        }
        catch (Exception excecao)
        {
            excecao.printStackTrace(System.err);
            System.exit(1);
        }
    }
    
    private final class MonitorSaidaProcesso extends Thread
    {
        private Process processo;

        public MonitorSaidaProcesso(Process processo)
        {
            this.processo = processo;
        }
        
        @Override
        public void run()
        {
            BufferedReader leitorTexto = null;
            InputStreamReader leitorFluxo = null;
            String linha = null;                    
            
            try
            {
                leitorFluxo = new InputStreamReader(processo.getErrorStream());
                leitorTexto = new BufferedReader(leitorFluxo);
                
                while (!leitorTexto.ready())
                {
                    Thread.sleep(1000);
                }
                
                System.err.println("Erro ao compilar as interfaces do arquivo Portugo.idl:\n\n");
                
                while ((linha = leitorTexto.readLine()) != null)
                {
                    System.err.println(linha);
                }
            }
            catch (Exception excecao)
            {
                excecao.printStackTrace(System.err);
            }
            finally
            {
                try { leitorTexto.close(); } catch (Exception excecao) { }
                try { leitorFluxo.close(); } catch (Exception excecao) { }
            }
            
            System.exit(1);
        }        
    }
}
