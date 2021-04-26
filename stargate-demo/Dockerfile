FROM openjdk:11

MAINTAINER Cedrick Lunven <cedrick.lunven@datastax.com>

########################################################
## Environment Variables
########################################################


VOLUME /tmp
ARG JAR_FILE=target/astra-portia-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

EXPOSE 8080
