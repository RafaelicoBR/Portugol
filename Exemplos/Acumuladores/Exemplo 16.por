/*fa�a um algoritmo que solicita ao usuario o numero inteiro e exibe na tela a metade do numero digitado */

programa
{
	funcao inicio (cadeia args[])
	{
		inteiro num 
		real metade

		escreva("Digite um n�mero inteiro \n")
		leia(num)

		metade = num /2.0 //obs: deve por ponto zero

		escreva("A metade do n�mero digitado �  ", metade)
		
	}
}
