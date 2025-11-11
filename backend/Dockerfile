# Stage 1: The Build Stage
FROM eclipse-temurin:21-jdk-jammy AS builder

# Set the working directory inside the container for the build process.
WORKDIR /build

# Copy the build artifact from your local machine.
COPY target/backend-0.0.1-SNAPSHOT.jar app.jar

# Stage 2: The Runtime Stage
# Use a minimal image with just the Java Runtime Environment (JRE).
# This will be the final, smaller image.
FROM eclipse-temurin:21-jre-jammy

# Create a new non-root user and group named usera for improved security.
# This user will run the application.
RUN groupadd -r usera && useradd -r -g usera -m -u 1001 usera

# Set the working directory for the application.
WORKDIR /app

# Install curl for health check
# Combine commands to minimize layers and clean up caches immediately.
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Copy the JAR file from the 'builder' stage. and set the ownership to the new user.
COPY --from=builder --chown=usera:usera /build/app.jar /app/app.jar

# Switch from the default 'root' user to usera.
USER usera

EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
