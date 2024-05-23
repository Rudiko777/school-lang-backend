FROM openjdk:21
WORKDIR /app
COPY target/school-languages-0.0.1-SNAPSHOT.jar my-spring-app.jar
EXPOSE 8080
CMD ["java", "-jar", "my-spring-app.jar"]