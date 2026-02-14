# Use official OpenJDK image
FROM eclipse-temurin:21-jdk-alpine

# Set working directory
WORKDIR /app

# Copy jar file
#COPY target/gpp-0.0.1-SNAPSHOT.jar app.jar
#COPY target/week-10-0.0.1-SNAPSHOT.jar app.jar
COPY target/*.jar app.jar


# Expose port

EXPOSE 8080

# Run the jar
ENTRYPOINT ["java","-jar","app.jar"]
