package de.cwansart.testcontainers.playground.docker_healthcheck.application.health;

import io.restassured.RestAssured;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class HealthcheckResourceIT {

    private static final Logger LOG = LoggerFactory.getLogger(HealthcheckResourceIT.class);

    @Rule
    public GenericContainer<?> service = new GenericContainer<>("docker-healthcheck:1.0-SNAPSHOT")
        .withExposedPorts(9080)
        .withLogConsumer(new Slf4jLogConsumer(LOG))
        .waitingFor(Wait.forHealthcheck());

    private String getResourceUrl() {

        String host = this.service.getContainerIpAddress();
        int port = service.getFirstMappedPort();
        String path = "/docker-healthcheck/api/healthcheck";

        return String.format("http://%s:%d%s", host, port, path);
    }

    @Test
    public void getHealth() {

        RestAssured.given()
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .when()
            .get(this.getResourceUrl()).then()
            .statusCode(Response.Status.NO_CONTENT.getStatusCode());
    }
}
