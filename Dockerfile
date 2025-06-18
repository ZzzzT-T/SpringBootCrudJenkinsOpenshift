# Use a lightweight Java 17 image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file built by Maven
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
