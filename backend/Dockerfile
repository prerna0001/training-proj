# Use official OpenJDK image
FROM openjdk:21-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy jar into container
COPY target/backend-0.0.1-SNAPSHOT.jar app.jar

# Expose port (Spring Boot default = 8080)
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
