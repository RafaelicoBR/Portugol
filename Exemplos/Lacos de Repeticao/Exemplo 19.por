/*Fa�a um algoritmo que apresente na tela a tabuada de multiplica��o dos n�meros de 1 at� 10.
O programa deve exibir o resultado das multiplica��es de 1x1, 1x2, ... at� 1x10, 
e recome�ar com 2x1, 2x2, ... at� 2x10, e seguir assim sucessivamente at� chegar em 10x10. 
Para isto, utilize um la�o de repeti��o com teste l�gico no final.*/
programa
{
	funcao inicio()
	{
	   inteiro tabuada, cont, numeros=1

	 	para(numeros=1; numeros<=10; numeros++ )
	 	{
	 		para(cont=1; cont<=10; cont++)
	 		{
	 			tabuada = numeros*cont
	 			escreva(numeros," X ",cont," = ",tabuada, "\n")
	 		}
	 	}
	}
}
