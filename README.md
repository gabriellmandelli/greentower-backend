# GreenTower-Api
Application created to register people who are part of Green Software.

## Requirements
- Java 8+
- Gradle
- PostgreSql

## Runing
Using the IDE IntelliJ IDEA configure the Main class and execute the project.

The database is hosted on Heroku

To access all endpoints in the Insomnia, import Json available on [Api-Docs](https://greentower-backend.herokuapp.com/v2/api-docs).  

The frontend of that application is in [greensoftware-frontend](https://github.com/gabriellmandelli/greentower-frontend).

## Available
- Email and password to login:
    ```
    {
        email: "greentower@greentower.com",
        password: "123456"
    }
    ```
- Backend deployed on HEROKU [available here](https://greentower-backend.herokuapp.com/)
- Frontend created with ReactJs and deployed on HEROKU [available here](https://greentower-frontend.herokuapp.com/)
- Documentation created by Swagger [available here](https://greentower-backend.herokuapp.com/swagger-ui.html#/)
- Docker hosted on [DockerHub](https://hub.docker.com/r/gabriellmandelli/greentower), to execute run:  
    ```
    docker pull gabriellmandelli/softplayer-java-apply:latest
    ```

## Dependencies
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
- [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
- [Spring Security](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-security)
- [JWT](https://jwt.io/)
- [FlywayDB](https://flywaydb.org/)
- [Swagger](https://swagger.io/)
- [ModelMapper](http://modelmapper.org/)

## Tools used
- [Spring Initializr](https://start.spring.io/)
- [Heroku](https://www.heroku.com/)
- [IntelliJIDE](https://www.jetbrains.com/idea/download/)
- [Insomnia](https://insomnia.rest/)
- [QueryPie](https://www.querypie.com/)
