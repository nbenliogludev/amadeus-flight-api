FROM openjdk:21-jdk

COPY target/amadeus-flight-api-0.0.1-SNAPSHOT.jar amadeus-flight-api-0.0.1-SNAPSHOT.jar

EXPOSE 8080:8080

ENTRYPOINT ["java","-jar","/amadeus-flight-api-0.0.1-SNAPSHOT.jar"]