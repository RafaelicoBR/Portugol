programa
{
	funcao inicio() 
	{
		inteiro n, i

		escreva("Digite um numero qualquer \n")
		leia(n)

		para(i=1; i<=n; i++)
		{
			se(i % 7 == 0)
			{
				escreva("� multiplo de 7  ", i, "\n")
			}senao
				escreva("N�o � multiplo de 7 ", i, "\n")
		}
	}
}
