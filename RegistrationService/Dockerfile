FROM openjdk:8-jdk-alpine

VOLUME /tmp

ARG JAR_FILE=target/RegistrationService-1.0-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} RegistrationService-1.0-SNAPSHOT.jar

CMD ["java","-jar","/RegistrationService-1.0-SNAPSHOT.jar"]