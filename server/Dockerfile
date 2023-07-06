FROM eclipse-temurin:17-jre-alpine
COPY build/libs/server-0.0.1-SNAPSHOT.jar server.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/server.jar" ]