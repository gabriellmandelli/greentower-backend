# Softplayer-java-apply
Aplicação criada conforme os requisitos em [Softplayer-Java-Apply](https://github.com/softplan/softplayer-java-apply).

## Requisitos
- Java 8+
- Gradle
- PostgreSql

## Para executar
Utilizando a IDE IntelliJ IDEA configurar a classe Main e executar o projeto.
O banco PostgreSQL já esta está hospedado no Heroku.

Para testar no Insomnia, importar o Json disponível em [Api-Docs](https://softplayer-apply-backend.herokuapp.com/v2/api-docs) contendo todos os metodos da aplicação.  

Obs.: o frontend dessa aplicação se encontra em [softplayer-apply-frontend](https://github.com/gabriellmandelli/softplayer-apply-frontend) contendo um README com as instruções para execução do mesmo.

## Disponível
* Email e senha para login:
    ```
    {
        email: "softplayer@softplayer.com",
        password: "123456"
    }
    ```
- Backend hospedado no HEROKU [disponível aqui](https://softplayer-apply-backend.herokuapp.com/)
- Frontend feito em ReactJs hospedado no HEROKU [disponível aqui](https://softplayer-apply-frontend.herokuapp.com/)
- Documentação criada com o SWAGGER [disponível aqui](https://softplayer-apply-backend.herokuapp.com/swagger-ui.html#/)
- Docker disponível em [DockerHub](https://hub.docker.com/r/gabriellmandelli/softplayer-java-apply):  
`
docker pull gabriellmandelli/softplayer-java-apply:latest
`

## Dependências utilizadas
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
- [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
- [Spring Security](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-security)
- [JWT](https://jwt.io/)
- [FlywayDB](https://flywaydb.org/)
- [Swagger](https://swagger.io/)
- [ModelMapper](http://modelmapper.org/)
- [Lombok](https://projectlombok.org/)

## Ferramentas Utilizadas
- [Spring Initializr](https://start.spring.io/)
- [Heroku](https://www.heroku.com/)
- IntelliJIDE
- Insomnia
- QueryPie