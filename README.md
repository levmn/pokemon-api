# Projeto PokéAPI

<div align="center">
  <img alt="snorlax" height="150" src="https://media.tenor.com/w33hdDzoSE0AAAAj/haunter.gif">
</div>

<br> 

Este é um projeto básico de API RESTful que utiliza a [PokéAPI](https://pokeapi.co/) para buscar dados de Pokémons,
salvá-los em um banco de
dados e exibir ou remover registros. Ele permite realizar operações **CRUD** básicas para gerenciar uma lista de
Pokémons.

## Estrutura do Projeto

- **.env.sample**: Arquivo de configuração na raiz do projeto que deve ser duplicado e renomeado para `.env`, onde serão
  inseridas as credenciais do banco de dados.
- **utils**: Diretório que contém o arquivo script SQL para criação das tabelas.

  ```
  src/
  ├── br/
      ├── com/
          ├── fiap/
              ├── utils/
                  └── pokemon.sql
  ```

## Instruções para Rodar o Projeto

1. Clone o repositório:

      ```bash
      git clone https://github.com/levmn/pokemon-api.git
      ```

      ```bash
      cd pokemon-api
      ```

2. Duplique o arquivo `.env.sample` para `.env` e insira as suas credenciais:

      ```
      DB_URL=jdbc:oracle:thin:@<host>:<port>:<service>
      DB_USER=<seu_usuario>
      DB_PASSWORD=<sua_senha>
      ```

3. **Inicie a aplicação:** Navegue até a classe Main em `src/br/com/fiap/app/Main.java` e a execute para iniciar o
   servidor HTTP.

A aplicação ficará disponível em http://localhost:8080/
