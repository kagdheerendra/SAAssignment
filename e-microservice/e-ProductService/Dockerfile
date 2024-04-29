FROM openjdk:17-jdk-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR demo
COPY --from=build target/e-ProductService-0.0.1-SNAPSHOT.jar productService.jar
ENTRYPOINT ["java", "-jar", "productService.jar"]