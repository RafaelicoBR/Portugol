programa
{
	funcao inicio()
	{
		real x

		escreva("Digite um n�mero \n")
		leia(x)

		limpa()
		
		se(x>=1 e x<=31){
			escreva("pertence ao intervalo entre 1 e 31")
		}senao{
			escreva(" n�o pertence ao intervalo entre 1 e 31")
		}
	}
}
