# Usar la imagen de Maven para compilar la aplicación
FROM maven:3.8.1-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Usar la imagen de OpenJDK para ejecutar la aplicación
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
