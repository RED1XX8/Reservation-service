FROM  eclipse-temurin:21-jdk-jammy AS build

WORKDIR /app

COPY build.gradle.kts .
COPY gradle gradle
COPY gradlew .
COPY settings.gradle.kts .

COPY reservation-service/ reservation-service/
COPY auth-service/ auth-service/

RUN chmod +x gradlew

RUN ./gradlew :auth-service:assemble -x test


FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=build /app/auth-service/build/libs/*.jar app.jar

EXPOSE 8081


ENTRYPOINT ["java" ,"-jar" ,"app.jar"]