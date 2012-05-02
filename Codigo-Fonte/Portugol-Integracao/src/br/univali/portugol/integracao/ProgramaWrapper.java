package br.univali.portugol.integracao;

import br.univali.portugol.integracao.asa.ArvoreSintaticaAbstrataPrograma;
import br.univali.portugol.integracao.execucao.Entrada;
import br.univali.portugol.integracao.execucao.ObservadorExecucao;
import br.univali.portugol.integracao.execucao.ResultadoExecucaoWrapper;
import br.univali.portugol.integracao.execucao.Saida;
import br.univali.portugol.nucleo.asa.TipoDado;
import br.univali.portugol.nucleo.execucao.ResultadoExecucao;
import java.util.ArrayList;
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
public final class ProgramaWrapper extends ProgramaPOA implements br.univali.portugol.nucleo.execucao.Entrada, br.univali.portugol.nucleo.execucao.Saida, br.univali.portugol.nucleo.execucao.ObservadorExecucao
{
    private POA rootPOA;
    private br.univali.portugol.nucleo.Programa programa;
    private Programa referencia = null;
    
    private Saida saida;
    private Entrada entrada;    
    private List<ObservadorExecucao> observadoresExecucao = new ArrayList<ObservadorExecucao>();

    public static Programa empacotar(br.univali.portugol.nucleo.Programa programaPortugol, POA rootPOA)
    {
        try
        {
            ProgramaWrapper programaWrapper = new ProgramaWrapper(programaPortugol, rootPOA);
            org.omg.CORBA.Object referencia = rootPOA.servant_to_reference(programaWrapper);
            Programa programaCORBA = ProgramaHelper.narrow(referencia);
            programaWrapper.referencia = programaCORBA;
            
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

    private ProgramaWrapper(br.univali.portugol.nucleo.Programa programa, POA rootPOA)
    {
        this.rootPOA = rootPOA;
        this.programa = programa;
        
        this.programa.setEntrada(ProgramaWrapper.this);
        this.programa.setSaida(ProgramaWrapper.this);
        this.programa.adicionarObservadorExecucao(ProgramaWrapper.this);
    }

    @Override
    public void adicionarObservadorExecucao(ObservadorExecucao observador)
    {
        if (!this.observadoresExecucao.contains(observador))
            this.observadoresExecucao.add(observador);
    }

    @Override
    public void removerObservadorExecucao(ObservadorExecucao observador)
    {
        this.observadoresExecucao.remove(observador);
    }

    @Override
    public void executar(String[] parametros)
    {
        programa.executar(parametros);
    }

    @Override
    public void interromper()
    {
        this.programa.interromper();
    }

    @Override
    public ArvoreSintaticaAbstrataPrograma getArvoreSintaticaAbstrata()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setFuncaoInicial(String funcaoInicial)
    {
        this.programa.setFuncaoInicial(funcaoInicial);
    }

    @Override
    public String getFuncaoInicial()
    {
        return this.programa.getFuncaoInicial();
    }

    @Override
    public void setEntrada(Entrada entrada)
    {
        this.entrada = entrada;
    }

    @Override
    public void setSaida(Saida saida)
    {
        this.saida = saida;
    }

    @Override
    public String getCodigo()
    {
        return this.programa.getCodigo();
    }

    @Override
    public boolean isExecutando()
    {
        return this.programa.isExecutando();
    }
    

    @Override
    public Object ler(TipoDado tipoDado) throws Exception
    {
        if (this.entrada != null)
        {
            return this.entrada.ler(br.univali.portugol.integracao.asa.TipoDado.from_int(tipoDado.ordinal()));
        }
        
        return null;
    }

    @Override
    public void limpar() throws Exception
    {
        if (this.entrada != null)
        {
            this.saida.limpar();
        }
    }

    @Override
    public void escrever(String valor) throws Exception
    {
        if (this.saida != null)
        {
            this.saida.escreverCadeia(valor);
        }
    }

    @Override
    public void escrever(boolean valor) throws Exception
    {
        if (this.saida != null)
        {
            this.saida.escreverLogico(valor);
        }
    }

    @Override
    public void escrever(int valor) throws Exception
    {
        if (this.saida != null)
        {
            this.saida.escreverInteiro(valor);
        }
    }

    @Override
    public void escrever(double valor) throws Exception
    {
        if (this.saida != null)
        {
            this.saida.escreverReal(valor);
        }
    }

    @Override
    public void escrever(char valor) throws Exception
    {
        if (this.saida != null)
        {
            this.saida.escreverCaracter(valor);
        }
    }

    @Override
    public void execucaoIniciada(br.univali.portugol.nucleo.Programa programa)
    {
        for (ObservadorExecucao observadorExecucao : observadoresExecucao)
        {
            observadorExecucao.execucaoIniciada(this.referencia);
        }
    }

    @Override
    public void execucaoEncerrada(br.univali.portugol.nucleo.Programa programa, ResultadoExecucao resultadoExecucao)
    {
        for (ObservadorExecucao observadorExecucao : observadoresExecucao)
        {
            observadorExecucao.execucaoEncerrada(this.referencia, ResultadoExecucaoWrapper.empacotar(resultadoExecucao, this.rootPOA));
        }
    }    
}
