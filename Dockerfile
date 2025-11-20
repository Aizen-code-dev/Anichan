# ============================
# Stage 1: Build with Java 21
# ============================
FROM maven:3.9.5-eclipse-temurin-21 AS build

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests


# ============================
# Stage 2: Runtime with Java 21 + yt-dlp + ffmpeg
# ============================
FROM eclipse-temurin:21-jdk

# Install python, pip, ffmpeg, yt-dlp
RUN apt-get update && \
    apt-get install -y python3 python3-pip ffmpeg && \
    pip3 install yt-dlp && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copy generated JAR
COPY --from=build /app/target/*.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
