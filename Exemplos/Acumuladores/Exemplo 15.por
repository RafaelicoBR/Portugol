programa
{
	funcao inicio(cadeia args[])
	{
		inteiro qpessoas, qmaquinasLavar, consumoMensal, consumoAgua

		escreva("digite a quantidade de moradores na casa \n")
		leia(qpessoas)

		escreva("digite a quantidade de m�quinas de lavar roupa \n")
		leia(qmaquinasLavar)

		consumoAgua = (qpessoas*80) + (qmaquinasLavar*200)/3
		consumoMensal = consumoAgua * 30

		escreva("O consumo de agua por dia �:  " , consumoAgua, " \n")
		escreva("O consumo mensal de agua desta residencia �:  ", consumoMensal)
	}
}
