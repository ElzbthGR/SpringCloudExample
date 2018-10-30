FROM openjdk:8-jdk-alpine

VOLUME /tmp

ARG JAR_FILE=target/CatService-1.0-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} CatService-1.0-SNAPSHOT.jar

CMD ["java","-jar","/CatService-1.0-SNAPSHOT.jar"]