# Etapa 1: build com Maven
FROM maven:3.9.9-amazoncorretto-21-alpine AS build

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline -B

COPY src src

RUN ./mvnw package -DskipTests

# Etapa 2: execução com JDK leve
FROM amazoncorretto:21.0.5-al2023-headless

WORKDIR /app

COPY --from=build /app/target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
