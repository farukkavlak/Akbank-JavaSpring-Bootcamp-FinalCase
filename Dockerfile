FROM openjdk:17-jdk AS builder-amd64
EXPOSE 9090
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]