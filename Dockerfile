# Use a base image with Java runtime
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the Gradle build
COPY build/libs/k82-0.0.1-SNAPSHOT.war /app/app.war

# Expose the port the application runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.war"]
