FROM eclipse-temurin:21.0.5_11-jre

ADD target/*.jar app.jar
RUN sh -c 'touch /app.jar'
EXPOSE 8081
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]