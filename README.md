# Currency Quotation Project

Repositório de estudo para praticar o fluxo básico do Spring MVC utilizando Spring Boot.

**Objetivo:** O projeto utiliza *Docker* para subir dois containers, um para rodar a API REST e outro para o banco de dados  PostgreSQL. A cada *x* quantidade de tempo uma schedule é executada fazendo uma requisição para uma API externa buscando  informações sobre o câmbio de algumas moedas em relação ao dolar e então persistidas na base de dados atualizando a cotação.

**Setup:** 
```
mvn package
```
Com *Docker* e *docker-compose* instalado em seu ambiente execute:
```
docker-compose up -d
```
Para acessar a documentação dos endpoints gerada pelo *Swagger* acesse:  http://localhost:8080/swagger-ui.html
