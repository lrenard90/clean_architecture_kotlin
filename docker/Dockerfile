# Build Stage
FROM gradle:8.9-jdk21 AS build
# Set the working directory
WORKDIR /home/gradle/project
# Copy the entire project into the container's working directory
COPY --chown=gradle:gradle ../* /home/gradle/project
# Run the Gradle build
RUN gradle build --no-daemon

# Run Stage
FROM eclipse-temurin:21-jdk-alpine
# Set the working directory
WORKDIR /app
# Expose port 8080
EXPOSE 8080
# Copy the built JAR file from the build stage to the run stage
COPY --from=build /home/gradle/project/build/libs/*.jar ./app.jar
# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]