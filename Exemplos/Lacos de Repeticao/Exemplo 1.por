/*Fa�a um programa para calcular a �rea de um tri�ngulo. Esse programa n�o pode permitir 
a entrada de dados inv�lidos, ou seja, medidas menores ou iguais a 0;*/
programa
{
	funcao inicio()
	{
		real base, altura, area

		escreva("Digite a base \n")
		escreva("Digite a altura \n")

		faca
		{
			leia(base)
			leia(altura)

			se(base>0 e altura>0)
			{
				area = (base*altura)/2
			}senao{
				escreva("Os numeros tem que ser maiores que zero \n")
			}
		}enquanto(base==0 e altura==0)
		
		escreva("A �rea �: ", area)
	}
}
