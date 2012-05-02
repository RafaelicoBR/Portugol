package br.univali.portugol.nucleo.asa;

/**
 * 
 * @author Luiz Fernando Noschang
 * 
 */
public final class NoReferenciaMatriz extends NoReferencia
{
    private NoExpressao linha;
    private NoExpressao coluna;

    public NoReferenciaMatriz(String nome, NoExpressao linha, NoExpressao coluna)
    {
        super(nome);
        this.linha = linha;
        this.coluna = coluna;
    }

    public NoExpressao getLinha()
    {
        return linha;
    }

    public NoExpressao getColuna()
    {
        return coluna;
    }

    @Override
    protected TrechoCodigoFonte montarTrechoCodigoFonte()
    {
        int tamanhoTexto = 0;

        int mLinha = getTrechoCodigoFonteNome().getLinha();
        int mColuna = getTrechoCodigoFonteNome().getColuna();

        tamanhoTexto = tamanhoTexto + getTrechoCodigoFonteNome().getTamanhoTexto() + linha.getTrechoCodigoFonte().getTamanhoTexto();
        tamanhoTexto = tamanhoTexto + 4 + coluna.getTrechoCodigoFonte().getTamanhoTexto();

        return new TrechoCodigoFonte(mLinha, mColuna, tamanhoTexto);
    }

    @Override
    public Object aceitar(VisitanteASA visitante) throws ExcecaoVisitaASA
    {
        return visitante.visitar(this);
    }
}
