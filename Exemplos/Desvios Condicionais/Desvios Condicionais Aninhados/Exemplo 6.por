programa
{
	funcao inicio(cadeia args[])
	{
		cadeia nome1, nome2
		inteiro idade1, idade2

		escreva("digite seu nome\n")
		leia (nome1)

		escreva("digite sua idade\n")
		leia (idade1)

		escreva("digite seu nome\n")
		leia (nome2)

		escreva("digite sua idade\n")
		leia (idade2)

		se(idade1 > idade2)
		{
			escreva(nome1, "  Voc� � a mais velha!!")
		}senao{
			escreva(nome2, "  Voc� � a mais velha!!!")
		}
	}
}
