programa 
{ 
	funcao inicio (cadeia args[]) { 

		real v,v0,a,t 
	
		escreva ("C�lculo de velocidades \n") 
		
		escreva ("Entre com a velocidade inicial (m/s) \n") 
		leia (v0) 
		
		escreva ("Entre com a acelera��o (m/s�) \n") 
		leia (a) 
		
		escreva ("Entre com o tempo (s) \n") 
		leia (t) 

		v = v0 + a*t 
		
		limpa()
		escreva ("A velocidade no instante t � \n ", v ,"m/s ") 
	} 
}
