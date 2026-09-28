FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk maven -y

COPY . .

RUN mvn clean install -DskipTests

FROM eclipse-temurin:17-jre-jammy

EXPOSE 8080

COPY --from=build /target/projeto_cadastro_login-0.0.1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
