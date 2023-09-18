FROM openjdk:17 as build

MAINTAINER sekh afrid

COPY target/loan-0.0.1-SNAPSHOT.jar loan-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/loan-0.0.1-SNAPSHOT.jar"]