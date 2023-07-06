FROM eclipse-temurin:17-jre-alpine
COPY build/libs/client-0.0.1-SNAPSHOT.jar client.jar
EXPOSE 8180
ENTRYPOINT [ "java", "-jar", "/client.jar" ]