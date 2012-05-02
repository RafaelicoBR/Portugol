INSERT INTO erro 
(
    aplicativo,	versao, mensagem, classe, stacktrace, 
	
    algoritmo, data, informacoesSO, informacoesJVM, macAddress
)
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
