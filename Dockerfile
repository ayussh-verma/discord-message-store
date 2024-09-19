# DEVELOPMENT ONLY
# Production builds should be made using the build-image plugin.
# This dockerfile exists only for docker compose watch support.

FROM eclipse-temurin:21-jdk-noble

COPY . .
RUN --mount=type=cache,target=/root/.m2 ./mvnw package -T 1C -Dmaven.test.skip
RUN cp target/discord-message-store-*.jar discord-message-store.jar

CMD [ "java", "-jar", "discord-message-store.jar" ]
