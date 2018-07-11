FROM openjdk:8-jre-alpine

ADD target/gtbapi.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]