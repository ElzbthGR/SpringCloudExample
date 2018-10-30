FROM openjdk:8-jdk-alpine

VOLUME /tmp

ARG JAR_FILE=target/registry-service-1.0.jar

# Add the application's jar to the container
ADD ${JAR_FILE} registry-service-1.0.jar

CMD ["java","-jar","/registry-service-1.0.jar"]