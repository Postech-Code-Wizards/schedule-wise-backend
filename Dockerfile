FROM maven:3.9.9-amazoncorretto-21-alpine AS build

WORKDIR /app

COPY ../pom.xml ./
COPY ../src ./src
COPY mvnw .
COPY .mvn .mvn

RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline -B

RUN mvn clean package -DskipTests

RUN ./mvnw package -DskipTests

# Etapa 2: execução com JDK leve
FROM amazoncorretto:21.0.5-al2023-headless

WORKDIR /app

COPY --from=build /app/target/*-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]