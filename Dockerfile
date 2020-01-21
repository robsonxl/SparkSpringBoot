FROM openjdk:8-jdk-alpine

RUN apk update && apk upgrade

LABEL maintainer="robsonxavierlima@gmail.com"

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=/nasa-kennedy-space/target/NasaKennedySpace-robson-xavier-1.0.0-SNAPSHOT.jar
ARG DATASET=/dataset/

ADD ${JAR_FILE} nasa-kennedy-space-by-rxl.jar
ADD ${DATASET} DATASET


ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/nasa-kennedy-space-by-rxl.jar"]


