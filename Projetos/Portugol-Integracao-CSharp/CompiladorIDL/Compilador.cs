using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.IO;
using System.Diagnostics;

namespace CompiladorIDL
{
    sealed class Compilador
    {
        static void Main(string[] args)
        {
            Compilador compilador = new Compilador();
            compilador.compilar();
        }

        private void compilar()
        {
            String nomeArquivoDLL = "portugol-integracao";
            String versaoArquivoDLL = "1.0.0.0";

            DirectoryInfo diretorioAplicacao = new DirectoryInfo(Path.GetDirectoryName(Application.ExecutablePath));
            DirectoryInfo diretorioProjetos = diretorioAplicacao.Parent.Parent.Parent.Parent;
            DirectoryInfo diretorioSaida = new DirectoryInfo(diretorioProjetos.FullName + "\\Portugol-Integracao-CSharp\\Portugol-Integracao-CSharp\\lib");
            FileInfo arquivoIDL = new FileInfo(diretorioProjetos.FullName + "\\Portugol-Integracao\\src\\br\\univali\\portugol\\integracao\\Portugol.idl");

            List<String> argumentos = new List<string>();
            
            argumentos.Add("-o");
            argumentos.Add("\"" + diretorioSaida.FullName + "\"");

            argumentos.Add("-asmVersion");
            argumentos.Add("\"" + versaoArquivoDLL + "\"");

            argumentos.Add("\"" + nomeArquivoDLL + "\"");
            argumentos.Add("\"" + arquivoIDL.FullName + "\"");
            
            ProcessStartInfo informacoesProcesso = new ProcessStartInfo("IDLToCLSCompiler", montarArgumentos(argumentos));

            informacoesProcesso.UseShellExecute = false;
            
            informacoesProcesso.RedirectStandardInput = true;
            informacoesProcesso.RedirectStandardOutput = true;
            informacoesProcesso.RedirectStandardError = true;
            
            Process processo = Process.Start(informacoesProcesso);
            StreamReader leitorSaida = processo.StandardOutput;

            Console.WriteLine(leitorSaida.ReadToEnd());

            if (processo.ExitCode == 0)
            {
                Console.WriteLine("As interfaces do arquivo Portugol.idl foram compiladas corretamente");
            }

            else
            {
                Console.WriteLine("Ocorreu um erro ao compilar as interfaces do arquivo Portugo.idl");
            }

            Console.WriteLine("Pressione uma tecla para sair...");
            Console.ReadLine();
        }

        private String montarArgumentos(List<String> argumentos)
        {
            String retorno = "";

            foreach (String argumento in argumentos)
            {
                retorno = retorno + " " + argumento;
            }

            return retorno;
        }
    }
}
