# --- Build stage ---
FROM gradle:8.10.1-jdk21-alpine AS builder
WORKDIR /workspace
COPY . .
RUN gradle clean :applications:app-service:bootJar --no-daemon

# --- Run stage (distroless) ---
FROM gcr.io/distroless/java21-debian12:latest
ENV SERVER_PORT=8081
EXPOSE 8081
# Ajusta el patrón si tu jar no es *-SNAPSHOT.jar
COPY --from=builder /workspace/applications/app-service/build/libs/*-SNAPSHOT.jar /app/app.jar
USER nonroot
ENTRYPOINT ["/usr/bin/java","-jar","/app/app.jar"]
