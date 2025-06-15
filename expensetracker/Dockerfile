# FROM openjdk:17-jdk-slim
# WORKDIR /app
# COPY target/expensetracker-0.0.1-SNAPSHOT.jar app.jar
# EXPOSE 8080
# CMD ["java", "-jar", "app.jar"]

# Use Maven to build the JAR
FROM maven:3.9.5-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy everything and build the app
COPY . .
RUN mvn clean package -DskipTests

# Use a lightweight JDK base to run the app
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy only the built jar from the previous stage
COPY --from=builder /app/target/*.jar app.jar

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]

