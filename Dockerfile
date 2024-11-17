#
# Build stage
#
FROM maven:3.8.2-jdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests



#
# Package stage
#
FROM openjdk:17-jdk-slim
COPY --from=build /target/back-0.0.1-SNAPSHOT.jar app.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]

#FROM amazoncorretto:21-alpine-jdk

#COPY target/back-0.0.1-SNAPSHOT.jar app.jar

#ENTRYPOINT [ "java", "-jar" , "/app.jar"]