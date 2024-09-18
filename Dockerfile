FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/websocket-project-0.0.1-SNAPSHOT.jar websocket-project-0.0.1-SNAPSHOT.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "websocket-project-0.0.1-SNAPSHOT.jar"]
