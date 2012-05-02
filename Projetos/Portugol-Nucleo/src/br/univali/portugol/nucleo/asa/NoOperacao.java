package br.univali.portugol.nucleo.asa;

/**
 * 
 * @author Luiz Fernando Noschang
 * 
 */
public final class NoOperacao extends NoExpressao
{
    private Operacao operacao;
    private NoExpressao operandoEsquerdo;
    private NoExpressao operandoDireito;
    private TrechoCodigoFonte trechoCodigoFonteOperador;

    public NoOperacao(Operacao operacao, NoExpressao operandoEsquerdo, NoExpressao operandoDireito)
    {
        this.operacao = operacao;
        this.operandoEsquerdo = operandoEsquerdo;
        this.operandoDireito = operandoDireito;
    }

    public Operacao getOperacao()
    {
        return operacao;
    }

    public NoExpressao getOperandoEsquerdo()
    {
        return operandoEsquerdo;
    }

    public NoExpressao getOperandoDireito()
    {
        return operandoDireito;
    }

    public void setTrechoCodigoFonteOperador(TrechoCodigoFonte trechoCodigoFonteOperador)
    {
        this.trechoCodigoFonteOperador = trechoCodigoFonteOperador;
    }

    public TrechoCodigoFonte getTrechoCodigoFonteOperador()
    {
        return trechoCodigoFonteOperador;
    }

    @Override
    protected TrechoCodigoFonte montarTrechoCodigoFonte()
    {
        TrechoCodigoFonte trechoCodigoFonte = operandoEsquerdo.getTrechoCodigoFonte();

        int linha = trechoCodigoFonte.getLinha();
        int coluna = trechoCodigoFonte.getColuna();
        int tamanhoTexto = trechoCodigoFonte.getTamanhoTexto() + ((getTrechoCodigoFonteOperador() != null) ? getTrechoCodigoFonteOperador().getTamanhoTexto() : 0) + operandoDireito.getTrechoCodigoFonte().getTamanhoTexto();

        return new TrechoCodigoFonte(linha, coluna, tamanhoTexto);
    }

    @Override
    public Object aceitar(VisitanteASA visitante) throws ExcecaoVisitaASA
    {
        return visitante.visitar(this);
    }
}
