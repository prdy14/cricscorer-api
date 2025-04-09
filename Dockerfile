FROM maven:3.9-eclipse-temurin-21 AS build
COPY . . 
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-alpine-3.21
COPY --from=build /target/cricscorer-api-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8081
ENTRYPOINT [ "java","-jar","demo.jar"]


