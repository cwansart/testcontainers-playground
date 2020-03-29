# NOTICE: You need to run `mvn clean package -DskipTests` before.
FROM openliberty/open-liberty:20.0.0.3-full-java8-openj9-ubi

COPY --chown=1001:0 src/main/liberty/config /config/
COPY --chown=1001:0 target/docker-healthcheck.war /config/apps

HEALTHCHECK --start-period=10s \
            --timeout=60s \
            --retries=10 \
            --interval=5s \
            CMD curl -f http://localhost:9080/docker-healthcheck/api/healthcheck || exit 1

RUN configure.sh
