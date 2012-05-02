using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.Remoting.Channels;
using Ch.Elca.Iiop;
using Ch.Elca.Iiop.Services;
using omg.org.CosNaming;
using System.Diagnostics;
using System.Windows.Forms;
using System.IO;
using System.Threading;

namespace br.univali.portugol.integracao.csharp
{
    public sealed class ServicoIntegracao
    {
        private const int PORTA_PADRAO = 53787;
        private const int PORTA_PADRAO_CALLBACK = 53788;

        private static ServicoIntegracao instance = null;
        
        private Portugol portugol = null;
        private IiopChannel canal = null;

        private ServicoIntegracao()
        {

        }

        public static ServicoIntegracao getInstance()
        {
            if (instance == null)
                instance = new ServicoIntegracao();

            return instance;
        }

        public Portugol iniciar()
        {
            string caminhoJARs = Path.GetDirectoryName(Application.ExecutablePath) + "\\lib\\".Replace("\n", "");
            string portugol_integracao_jar = caminhoJARs + "Portugol-Integracao.jar";

            ProcessStartInfo construtorProcesso = new ProcessStartInfo();
            construtorProcesso.FileName = "java";
            construtorProcesso.Arguments = "-jar \"" + portugol_integracao_jar + "\"";
            construtorProcesso.CreateNoWindow = false;
            construtorProcesso.WindowStyle = ProcessWindowStyle.Hidden;

            Process.Start(construtorProcesso);

            Thread.Sleep(5000);
            
            canal = new IiopChannel(PORTA_PADRAO_CALLBACK);
            ChannelServices.RegisterChannel(canal, false);

            CorbaInit init = CorbaInit.GetInit();
            
            NamingContext nameService = init.GetNameService("localhost", PORTA_PADRAO);
            NameComponent[] name = new NameComponent[] { new NameComponent("Portugol", "") };

            portugol = (Portugol) nameService.resolve(name);
            
            return portugol;
        }

        public void encerrar()
        {
            portugol.encerrar();
        }
    }
}
