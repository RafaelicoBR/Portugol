programa
{
	funcao inicio()
	{
		real resultado, oper1, oper2	
		caracter opcao

		escreva("Digite doi operadores \n")
		leia(oper1, oper2)

		escreva("Agora escolha uma das op��es:\n")
		escreva("+ ->  Para Soma \n")
		escreva("- -> Para Subtra��o \n")
		escreva("/ -> Para Divis�o \n")
		escreva("* -> Para Multiplica��o \n")

		leia(opcao)
		
		se(opcao == '+')
		{
			resultado = oper1 + oper2
			
		} senao {
			se(opcao == '-')
			{
				resultado = oper1 - oper2
				
			} senao {
				se(opcao == '/')
				{
					resultado = oper1 / oper2
					
				} senao {
					se(opcao == '*')
					{
						resultado = oper1 * oper2
					}	
				}
			}
		}
		escreva("O resultado da opera��o: ", resultado)
	}
}
