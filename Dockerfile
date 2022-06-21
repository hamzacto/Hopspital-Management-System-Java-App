FROM maven:3.8.4-jdk-8
WORKDIR .
COPY . .
RUN mvn clean install
CMD mvn spring-boot:run

FROM openjdk:8-jdk-alpine
WORKDIR .
COPY target/app.jar app.jar
ENTRYPOINT ["java","-jar","/message-server-1.0.0.jar"]
