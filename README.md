### Finalização do Trabalho da Pos Graduação IGTI

## Project Arch
	- Project BackEnd: Spring Cloud 2.7.3 
	- Essa versão esta sendo utilizada devido a limitação do Camunda versão 7.18
	
## Owner
	- Carlos Roberto Medeiros de Lima
	
<p align="center">
  <img src= "https://github.com/CarlosRobertoMedeiros/posigti/blob/main/flow-principal/fluxo-principal.png" />
</p>
## Docker Implementation
  Para carregar a estrutura, execute:
    sh start-constainer.sh
  Para encerrar a estrutura, execute:
    sh stop-constainer.sh


## Links de Acesso
  - Para Acessar o Orquestrador Camunda
	- http://localhost:8011/orquestrador-camunda/
	- user: demo password: demo

  - Para Acessar os microserviços	
	- service-cadastro-negativo
		- localhost:8031/service-cadastro-negativo/swagger-ui
	- service-cadastro-positivo
		- localhost:8021/service-cadastro-positivo/swagger-ui
	- service-fraude
		- localhost:8031/service-fraude/swagger-ui
	- service-clientes
		- localhost:8031/service-clientes/swagger-ui
	- service-hub-mensagens
		- localhost:8031/service-hub-mensagens/swagger-ui
	
## Collection do Postman
  No diretorio postman-collections
  Basta importar a collection na ferramenta

## Link indicativo para ajudar no desenvolvimento
 
  
  
  
  
## Cloud Implementation
	
	
	
		
	
	
	

