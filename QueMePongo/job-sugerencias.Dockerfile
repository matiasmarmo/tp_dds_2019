FROM openjdk:8

VOLUME /tmp

ARG JAR_FILE=target/que-me-pongo-job-sugerencias.jar

ADD ${JAR_FILE} que-me-pongo-job-sugerencias.jar

ENTRYPOINT ["java","-jar","/que-me-pongo-job-sugerencias.jar"]