FROM openjdk:17-jdk-alpine

EXPOSE 8081

LABEL authors="darya"

ADD build/libs/Authorization-0.0.1.jar myapp.jar

ENTRYPOINT ["java", "-jar", "myapp.jar"]