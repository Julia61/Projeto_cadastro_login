FROM ubuntu:latest AS build

RUN apt-get update && apt-get install -y openjdk-17-jdk maven

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:resolve

COPY . .

RUN mvn clean install

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /app/target/projeto_cadastro_login-0.0.1.jar app.jar

# Comando de entrada
ENTRYPOINT [ "java", "-jar", "app.jar" ]
