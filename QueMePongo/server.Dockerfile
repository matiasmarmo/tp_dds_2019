FROM openjdk:8

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=target/que-me-pongo-server.jar

ADD ${JAR_FILE} que-me-pongo-server.jar

ENTRYPOINT ["java","-jar","/que-me-pongo-server.jar"]