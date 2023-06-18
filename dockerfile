FROM openjdk:11-jdk-slim
MAINTAINER IRypS some@gmail.com
COPY target/ms-curso-0.0.1-SNAPSHOT.jar ms-curso.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "ms-curso.jar"]