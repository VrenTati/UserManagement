FROM openjdk

COPY target/springboot-mysql-docker.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "springboot-mysql-docker.jar"]