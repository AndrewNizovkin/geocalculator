FROM ubuntu:jammy
RUN apt update && apt install openjdk-21-jdk -y
WORKDIR /home/andrew/MyJava/geocalculator/geocalculator_service
COPY target/geocalculator_service-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "geocalculator_service-0.0.1-SNAPSHOT.jar"]