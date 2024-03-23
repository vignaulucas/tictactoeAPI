FROM openjdk:latest

COPY target/tic_tac_toe-api-*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]



