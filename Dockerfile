# Multi-stage build for smaller image
FROM openjdk:17-jdk-slim as builder

WORKDIR /app
COPY . .
RUN ./gradlew clean bootJar

FROM openjdk:17-jdk-slim

WORKDIR /app
COPY --from=builder /app/build/libs/umc-0.0.1-SNAPSHOT.jar app.jar

# Set timezone
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

EXPOSE 8080

ENTRYPOINT ["java", "-Duser.timezone=Asia/Seoul", "-Dspring.profiles.active=prod", "-jar", "/app/app.jar"]