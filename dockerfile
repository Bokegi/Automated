#
# BUILD STAGE
#
FROM maven:3.6.0-jdk-11-slim AS build  
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
COPY checkstyle.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package 
#
# PACKAGE STAGE
#
FROM openjdk:11-jre-slim 
COPY target/progetto-1.0-SNAPSHOT.jar /usr/app/progetto-1.0-SNAPSHOT.jar  
EXPOSE 8080  
CMD ["java","-jar","/usr/app/progetto-1.0-SNAPSHOT.jar"]  