FROM openjdk:11
COPY target/rest-api-1.0-SNAPSHOT.jar /usr/src/myapp/rest-api.jar
WORKDIR /usr/src/myapp
CMD ["java","-jar", "rest-api.jar"]
