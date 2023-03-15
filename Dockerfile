FROM gradle:8.0-jdk17 as builder
COPY --chown=gradle:gradle . /home/src
WORKDIR /home/src
RUN gradle build

FROM eclipse-temurin:17-jre-alpine
CMD mkdir /app
COPY --from=builder /home/src/build/libs/spring-boot-oauth2-resource-server-keycloak-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
ENTRYPOINT java -jar /app.jar