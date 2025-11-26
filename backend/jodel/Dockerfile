### STAGE 1: Build ###
FROM maven:3.8.4-openjdk-17 AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn dependency:go-offline -B
RUN mvn package -DskipTests


### STAGE 2: Run ###
FROM eclipse-temurin:17
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","app.jar"]