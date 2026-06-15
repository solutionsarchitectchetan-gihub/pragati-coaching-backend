FROM amazoncorretto:21 AS builder
WORKDIR /build
COPY gradle gradle
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .
RUN ./gradlew dependencies --no-daemon || true
COPY src src
RUN ./gradlew bootJar --no-daemon

FROM amazoncorretto:21-alpine
WORKDIR /app
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser
COPY --from=builder /build/build/libs/pragati-coaching-backend.jar app.jar
EXPOSE 8080
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]
