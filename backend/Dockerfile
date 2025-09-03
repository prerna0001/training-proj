# Stage 1: The Build Stage
FROM openjdk:21-jdk-slim AS builder

# Set the working directory inside the container for the build process.
WORKDIR /build

# Copy the build artifact from your local machine.
COPY target/backend-0.0.1-SNAPSHOT.jar app.jar

# Stage 2: The Runtime Stage
# Use a minimal image with just the Java Runtime Environment (JRE).
# This will be the final, smaller image.
FROM eclipse-temurin:21-jre-jammy

# Set the working directory for the application.
WORKDIR /app

# Install curl for health check
# Combine commands to minimize layers and clean up caches immediately.
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Copy the JAR file from the 'builder' stage.
COPY --from=builder /build/app.jar app.jar

# Expose port (Spring Boot default = 8080)
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
