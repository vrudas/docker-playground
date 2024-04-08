FROM eclipse-temurin:21-jdk-alpine

LABEL authors="vrudas"

WORKDIR /home/app
COPY ./build/libs/*.jar app.jar

EXPOSE 8080
EXPOSE 8090

ENTRYPOINT ["java", "-jar", "app.jar"]
