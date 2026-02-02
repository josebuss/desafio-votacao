# Desafio Votação

Projeto backend desenvolvido em **Java 17** com **Spring Boot 3.5.10**, utilizando **Spring Data JPA**, **Flyway** para versionamento do banco de dados e **H2** como banco relacional em modo arquivo.

---

# Resolução do problema

## Objetivo principal

Sistema desenvolvido para atender à necessidade de gerenciar votações de pautas de uma cooperativa.

O usuário **gestor** poderá, por meio dos endpoints desenvolvidos, criar novas pautas e consultá-las, bem como abrir novas sessões de votação, limitando-se a apenas uma sessão ativa por pauta. Também será possível consultar sessões ativas e/ou inativas.

Foi permitido o cadastro de n sessões por pauta, considerando cenários em que a votação precise ser refeita.

Como gestor, também será possível consultar o resultado consolidado da última sessão de votação de uma pauta.

Para atender ao cooperado, que realizará a votação, foi disponibilizado um endpoint que permite efetuar o voto em uma sessão aberta.

---

## Tarefas bônus

### Tarefa Bônus 01
Para atender ao critério da Tarefa Bônus 01, foi criado um endpoint que realiza a chamada de um client externo, o qual retorna, de forma aleatória, se o documento do cooperado está apto, inapto ou inválido.

### Tarefa Bônus 02
Para atender à Tarefa Bônus 02, foram considerados dois pontos:

1. O endpoint de cálculo persiste o resultado da última sessão encerrada em uma tabela específica, evitando recálculos desnecessários. Caso uma nova sessão seja encerrada, o sistema realiza automaticamente um novo cálculo do resultado.
2. Considerando a quantidade de votos que a API pode receber, foi desenvolvido um teste simples de performance utilizando o JMeter (detalhes nas seções seguintes).

### Tarefa Bônus 03
Para atender à Tarefa Bônus 03, foi adotado o versionamento dos endpoints via URL, o que torna a manutenção mais simples e facilita futuras migrações e novas configurações com menor impacto.

---

## Estrutura de tabelas

Para atender à demanda de votação de pautas, foram criadas as seguintes tabelas:

- Tabela para cadastro de **pautas**, contendo os campos **código** e **descrição**.
- Tabela para registro das **sessões de votação**, contendo a pauta e a duração da sessão, com relacionamento **muitos para um** com a pauta.
- Tabela para registro dos **votos**, contendo a sessão, o documento de identificação do cooperado e o voto (**sim/não**).
- Tabela para registrar o **resultado da votação** da última sessão encerrada de cada pauta.


---

## Teste de performace

Importar o arquivo na ferramenta Jmeter.

``` 
Desafio Votação.jmx 
```

Link da ferramenta
``` 
https://jmeter.apache.org/download_jmeter.cgi 
```
---

## Documentação da API (Swagger)

Com a aplicação em execução, a documentação interativa dos endpoints pode ser acessada em:

**[http://localhost:8080/desafio/votacao/swagger-ui/index.html](http://localhost:8080/desafio/votacao/swagger-ui/index.html)**


---

## Acesso ao H2 Console

Com a aplicação em execução, o console do H2 pode ser acessado em:

**[http://localhost:8080/h2-console](http://localhost:8080/desafio/votacao/h2-console)**


---

## Como executar o projeto

### Pré-requisitos

* Java 17 instalado
* Maven 3.8+

### Executar a aplicação

```bash
mvn spring-boot:run
```

Ou executar a classe principal:

```java
DesafiovotacaoApplication.java
```

A aplicação será iniciada em:

```
http://localhost:8080/desafio/votacao/
```

---

## Banco de Dados (H2)

O projeto utiliza **H2 em modo arquivo**, garantindo persistência dos dados localmente.

### Configuração do banco

* **JDBC URL:** `jdbc:h2:file:./data/banco-h2`
* **Usuário:** `sa`
* **Senha:** *(em branco)*

---

## Versionamento de Banco de Dados (Flyway)

O Flyway é utilizado para controle de versão do schema do banco de dados.

### Localização dos scripts

```
src/main/resources/db/migration
```

### Convenção

```
V1__comando_tabela.sql
```

* Os scripts são executados automaticamente na inicialização da aplicação
* A tabela `flyway_schema_history` controla as migrações aplicadas

---
## Observações

* O banco H2 é recriado automaticamente caso a pasta `/data` seja removida
* As migrations do Flyway serão reaplicadas conforme controle de versão
* Estrutura preparada para futura migração para PostgreSQL ou outro banco relacional

---

## JPA / Hibernate

* Estratégia de DDL configurada como:

```properties
spring.jpa.hibernate.ddl-auto=validate
```
O Hibernate **não cria nem altera tabelas**, apenas valida o schema gerenciado pelo Flyway.

---

## Testes unitários

O projeto inclui suporte a testes automatizados utilizando:

- **JUnit 5** para execução dos testes
- **Mockito** para criação de mocks
- **H2** como banco de dados em memória para testes de repositório e integração

### Executando os testes

```bash
mvn test
```

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.5.10
- Spring Web
- Spring Data JPA
- Bean Validation
- Flyway
- H2 Database (file mode)
- Maven
- Lombok
- MapStruct
- JUnit 5
- Mockito

---