FROM maven:3.9-eclipse-temurin-25 AS builder

ARG PROJECT_DIR
WORKDIR /app

COPY ${PROJECT_DIR}/pom.xml ./pom.xml
RUN mvn dependency:go-offline -B

COPY ${PROJECT_DIR}/src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:25-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
