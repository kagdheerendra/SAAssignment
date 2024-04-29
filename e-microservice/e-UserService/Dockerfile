FROM openjdk:17-jdk-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR demo
COPY --from=build target/e-UserService-0.0.1-SNAPSHOT.jar userService.jar
ENTRYPOINT ["java", "-jar", "userService.jar"]