/*Leia um n�mero inteiro. Se o n�mero lido for positivo, escreva uma mensagem indicando se ele � par ou �mpar.*/
programa
{
	funcao inicio(cadeia argas[])
	{
		inteiro num

		escreva("Digite um numero positivo\n")
		leia(num)

		se(num > 0){
			se(num % 2==0)
			{
				escreva("O n�mero: ",num, "  � par \n")
			}senao{
				escreva("O n�mero: ",num, "  � �mpar \n")
			}
		}
	}
}
