FROM openjdk:8 as build
ARG JAR_FILE=Projet9_MediscreenNotes-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]