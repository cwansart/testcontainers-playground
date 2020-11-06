FROM maven AS build

COPY . src

RUN mvn package

# NOTICE: You need to run `mvn clean package -DskipTests` before.
FROM open-liberty:20.0.0.3-full-java8-openj9

COPY --from=build src/main/liberty/config /config/
COPY --from=build target/docker-healthcheck.war /config/apps

HEALTHCHECK --start-period=10s \
            --timeout=60s \
            --retries=10 \
            --interval=5s \
            CMD curl -f http://localhost:9080/docker-healthcheck/api/healthcheck || exit 1

RUN configure.sh
