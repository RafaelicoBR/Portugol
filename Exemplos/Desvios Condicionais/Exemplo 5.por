programa 
{
	funcao inicio (cadeia args[]) 
	{ 
		real m1, m2, m3, media 
				
		escreva ("Informe a m�dia 1 \n")
		leia (m1) 
		escreva ("Informe a m�dia 2 \n")
		leia (m2)
		escreva ("Informe a m�dia 3 \n")
		leia (m3)
		
		media = (m1+m2+m3)/3
		se (media >= 6) {
			escreva("aprovado  ",media, ) 
		}senao {
			escreva("reprovado  ",media) 
		} 
	}
}
