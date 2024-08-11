# Base image
FROM openjdk:17

# Copy the JAR file into the container
COPY build/libs/app.jar /app.jar

# Set the entry point for the container
ENTRYPOINT ["java", "-jar", "/app.jar"]