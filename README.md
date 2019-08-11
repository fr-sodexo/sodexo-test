# Documentação - Desafio Android Sodexo

Foi desenvolvido um aplicativo em Kotlin com as seguintes funcionalidades:  
* Lista de Filmes do Trakt sem paginação com o título e o ano de lançamento.  
* Detalhes de Filme do Trakt ao clicar em uma filme na lista.  
* Detalhes contém título, ano de lançamento, avaliação, resumo e link para página do IMDB.  

## Bibliotecas Usadas  
* Retrofit, para chamadas de APIs.  
* Gson, para conversão de Json em objetos Kotlin.  
* RxJava, para gerenciamento de Threads.  

## APIs Usadas  
* https://api.trakt.tv/movies/popular, para Lista de Filmes.  
* https://api.trakt.tv/movies/{id}?extended=full, para Detalhes de Filme.  

## Estrutura do Projeto  
Usado um MVC com a seguinte estrutura:  
* /model, para gerenciamento de dados e integrações.  
* /model/schema, para definições de objetos.  
* /model/api, para gerenciamento de integrações de APIs.  
* /model/api/config, para configuração do Retrofit e de URLs/Endpoints. 
* /model/api/endpoints, para declaração das interfaces de endpoints do Retrofit.  
* /model/api/manager, para gerenciador de request/parse do Retrofit.  
* /controller, para controle de dados e conexão entre telas e integrações, usando o padrão de Repositório.  
* /view, para controle de telas.  
* /view/component, para componentes "burros" reutilizáveis entre telas.  
* /view/container, para as telas em si.  
* /view/main, para a tela principal, navegação ocorre via troca de fragmentos.  
* /view/movie, para telas referentes à funcionalidade de filmes.  
