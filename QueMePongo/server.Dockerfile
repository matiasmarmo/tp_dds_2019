FROM openjdk:8

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=target/que-me-pongo-server.jar

ADD ${JAR_FILE} que-me-pongo-server.jar

COPY gcp-credentials.json /data/gcp-credentials.json

ENV GOOGLE_APPLICATION_CREDENTIALS=/data/gcp-credentials.json

ENTRYPOINT ["java","-jar","/que-me-pongo-server.jar"]
