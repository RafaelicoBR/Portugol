programa
{
	funcao inicio (cadeia args[])
	{
		real m1, m2, m3, media 
		
		escreva ("Informe a m�dia 1: \n" )
		leia (m1)
		escreva( "Informe a m�dia 2: \n") 
		leia (m2)
		escreva ("Informe a m�dia 3: \n") 
		leia (m3)
		
		media = (m1+m2+m3)/3 
		escreva ("M�dia: ", media) 


		se (m1 < media){ 
			escreva ("\n M�dia 1 � menor que a m�dia", media) 
		}
		
		se (m2 < media) {
			escreva ("\nM�dia 2 � menor que a m�dia", media )
		}
		
		se (m3 < media) {
			escreva ("\nM�dia 3 � menor que a m�dia", media )
		}
		
	}
}