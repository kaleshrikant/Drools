FROM eclipse-temurin:21-jdk as build

WORKDIR /app

COPY gradlew build.gradle settings.gradle ./
COPY gradle/ ./gradle/
COPY src ./src

RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
