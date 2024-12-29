FROM maven:3.9.7-amazoncorretto-17 AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app

RUN mvn clean package

FROM amazoncorretto:17-alpine-jdk

COPY --from=build /app/target/projeto_cadastro_login-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
