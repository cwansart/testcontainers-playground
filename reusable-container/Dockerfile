FROM open-liberty:20.0.0.3-full-java8-openj9

COPY --chown=1001:0 src/main/liberty/config /config/
COPY --chown=1001:0 target/reusable-container.war /config/apps

RUN configure.sh
