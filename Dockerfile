FROM openjdk:8-jdk-alpine

EXPOSE 8080

LABEL authors="darya"

ENTRYPOINT ["top", "-b"]

COPY build/libs/Authorization-0.0.1-SNAPSHOT.jar myapp.jar

CMD ["java", "-jar", "myapp.jar"]