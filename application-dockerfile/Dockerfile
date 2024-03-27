FROM eclipse-temurin:21-jdk-alpine

LABEL authors="vrudas"

WORKDIR /home/app
COPY ./build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
