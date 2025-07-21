# Use an official JDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /employee-service

# Copy the JAR file into the container
COPY target/employee-service-0.0.1-SNAPSHOT.jar employee-service.jar

# Expose port 8081
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "employee-service.jar"]