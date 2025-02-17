# Use a base image that has JDK installed
FROM gcr.io/distroless/java21

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar file from host to container
COPY paybackpal/target/paybackpal-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar", "-X"]