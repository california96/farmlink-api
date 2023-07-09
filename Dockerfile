FROM amazoncorretto:17-alpine-jdk

ARG JAR_FILE=build/libs/farmlink-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} farmlink-api-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/farmlink-api-0.0.1-SNAPSHOT.jar"]

