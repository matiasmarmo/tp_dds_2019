FROM openjdk:8

VOLUME /tmp

ARG JAR_FILE=target/que-me-pongo-job-alertas.jar

ADD ${JAR_FILE} que-me-pongo-job-alertas.jar

COPY gcp-credentials.json /data/gcp-credentials.json

ENV GOOGLE_APPLICATION_CREDENTIALS=/data/gcp-credentials.json

ENTRYPOINT ["java","-jar","/que-me-pongo-job-alertas.jar"]
