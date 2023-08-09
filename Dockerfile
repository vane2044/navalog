FROM eclipse-temurin:11.0.17_8-jdk-alpine
VOLUME /TMP
COPY target/*.jar app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar"]
EXPOSE 8080
