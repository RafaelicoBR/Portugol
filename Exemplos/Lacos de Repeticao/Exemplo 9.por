programa
{
	funcao inicio()
	{
		inteiro cont=0 
		inteiro numeros, m

		escreva("Digite um numero para saber quais s�o seus multiplos\n")
		leia(m)

		escreva("Digite 10 numeros inteiros e positivos\n")

		enquanto(cont<20)
		{
			leia(numeros)
			
			se(numeros>0 e numeros%m==0)
			{
				escreva("O n�mero   ", numeros, "  � multiplo de  ",m, "\n")
			}		

			cont++
		}
	}
}
