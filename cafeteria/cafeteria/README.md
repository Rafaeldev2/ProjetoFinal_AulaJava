# Passo-a-Passo do Mão na Massa

## Organização do Projeto

O projeto está organizado da seguinte forma:

- `./bin`: diretório onde os arquivos compilados Java são armazenados e atualizados
- `./config`: diretório onde os arquivos de configuração (ex. senhas e usuários)
- `./docs`: arquivos de documentação do projeto
- `./lib`: diretório que contém as dependências (ex. bibliotecas)
- `./sql`: scripts de banco para criar e popular dados ao iniciar a base de dados
- `./src`: código-fonte do projeto Java

## Configuração do Ambiente

Edite o arquivo `.env` caso precise alterar as variáveis do ambiente do banco de dados.

*Dica:* armazene os seus arquivos `properties` neste diretório.

## Deploy dos Serviços

Posicione o terminal no diretório que contém o arquivo `docker-compose.yml` e execute:

```sh
docker compose up
```

Para finalizar a execução você pode:

- no mesmo terminal pressionar `Ctrl+C`
- abrir o Docker Desktop, em Containers e pressionar o `stop` (botão quadrado)

## Conexão com pgAdmin

### Para execução via Docker

Execute os seguintes passos:

- acesse o endereço [http://localhost:8880]
- efetue o login com o usuário e senha que consta no arquivo `.env`
- registre uma nova conexão usando os seguintes parâmetros:
    - address: `postgres`
    - port: `5438`
    - database: `cafeteria`
    - usuário e senha: os que constam no `.env`

### Para execução via instalação local com Postgres local

Execute os seguintes passos:

- inicie o pgAdmin
- efetue o login com o usuário e senha que cadastrou
- registre uma nova conexão usando os seguintes parâmetros:
    - address: `localhost`
    - port: `5432`
    - database: `postgres`
    - usuário: `postgres`    
    - senha: a senha cadastrada na instalação do Postgres

**IMPORTANTE**: Nunca crie, altere ou remova algo da database `postgres` para não corromper a instalação.

## Conexão com DBeaver

### Para conexão com o Postgres via Docker

Preencha os seguintes parâmetros:

- host: `localhost`
- port: `5432`
- database: `cafeteria`
- usuário e senha: os que constam no `.env`

### Para conexão com o Postgres local

Preencha os seguintes parâmetros:

- host: `localhost`
- port: `5432`
- database: `postgres`
- usuário: `postgres`    
- senha: a senha cadastrada na instalação do Postgres

**IMPORTANTE**: Nunca crie, altere ou remova algo da database `postgres` para não corromper a instalação.
