# syntax=docker/dockerfile:experimental
#### Stage 1: Build the application
FROM eclipse-temurin:21-jdk-alpine as build

# Set the current working directory inside the image
WORKDIR /app

# Copy maven executable to the image
COPY mvnw .

# Set the maven executable to executable
RUN chmod +x ./mvnw

# Copy the Maven Wrapper
COPY .mvn .mvn

# Copy the pom.xml file
COPY pom.xml .

# Copy the project source
COPY src src

# Install the dependencies and package the application
RUN --mount=type=cache,target=/root/.m2 ./mvnw install -DskipTests

# Copy the application artifacts
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

#### Stage 2: A minimal docker image with command to run the app
FROM eclipse-temurin:21-jre-alpine
VOLUME /tmp
ARG DEPENDENCY=/app/target/dependency

RUN addgroup -S demo && adduser -S demo -G demo
USER demo

# Copy project dependencies from the build stage
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","com.devsu.bank.account_service.AccountServiceApplication"]
