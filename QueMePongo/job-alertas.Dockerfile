FROM openjdk:8

VOLUME /tmp

ARG JAR_FILE=target/que-me-pongo-job-alertas.jar

ADD ${JAR_FILE} que-me-pongo-job-alertas.jar

ENTRYPOINT ["java","-jar","/que-me-pongo-job-alertas.jar"]