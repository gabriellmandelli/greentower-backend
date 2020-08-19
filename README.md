# Softplayer-java-apply
Aplicação criada conforme os requisitos em [Softplayer-Java-Apply](https://github.com/softplan/softplayer-java-apply) 

## Requisitos
- Java 8+
- Gradle
- PostgreSql

## Ferramentas Utilizadas
- IntelliJIDE
- VS Code
- Insomnia

## Rodando Docker
```
cd softplayer-java-apply
sudo docker build -t softplayer .
sudo docker images
docker run -t -p 8000:8080 --name dockerize softplayer
```