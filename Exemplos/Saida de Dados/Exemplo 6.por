programa 
{ 
	funcao inicio(cadeia args[])
	{ 
		real y, w, x, z, totalGasto 
		
		escreva("1Kg da ma�a ->1.50 \n") 
		escreva("1kg de banana ->2.00 \n") 
		
		escreva("Digite a quantidade de ma�a em quilos \n") 
		leia(x) 
		escreva("Digite a quantidade de banana em quilos \n") 
		leia(z) 
		y = 1.50 * x 
		w = 2.00 * z 
		totalGasto = y + w 
		
		escreva("O total gasto em ma�as foi: ", y, "\n") 
		escreva("O total gasto em bananas foi: ", w, "\n") 
		escreva("O total gasto nas frutas foi: ", totalGasto, "\n") 
	} 
}