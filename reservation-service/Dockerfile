FROM eclipse-temurin:21-jdk-jammy AS build

WORKDIR /app

COPY build.gradle.kts .
COPY gradle gradle
COPY settings.gradle.kts .
COPY gradlew .
COPY reservation-service/ reservation-service/
COPY auth-service/ auth-service/

RUN chmod +x gradlew

RUN ./gradlew :reservation-service:assemble -x test



FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=build /app/reservation-service/build/libs/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java" , "-jar" , "app.jar"]