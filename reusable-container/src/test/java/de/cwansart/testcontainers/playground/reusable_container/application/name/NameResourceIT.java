package de.cwansart.testcontainers.playground.reusable_container.application.name;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.Matchers.is;

public class NameResourceIT {

    private static final Logger LOG = LoggerFactory.getLogger(NameResourceIT.class);

    public static GenericContainer<?> service;

    @BeforeClass
    public static void setUp() {

        service = new GenericContainer<>("reusable-container:1.0-SNAPSHOT")
            .withExposedPorts(9080)
            .withReuse(true)
            .withLogConsumer(new Slf4jLogConsumer(LOG))
            .waitingFor(Wait.forLogMessage(".*Web application available.*", 1));
        service.start();
    }

    private String getResourceUrl() {

        String host = service.getContainerIpAddress();
        int port = service.getFirstMappedPort();
        String path = "/reusable-container/api/names";

        return String.format("http://%s:%d%s", host, port, path);
    }

    @Test
    public void getNames() {

        RestAssured.given()
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .when()
            .get(this.getResourceUrl()).then()
            .statusCode(Response.Status.OK.getStatusCode())
            .contentType(MediaType.APPLICATION_JSON)
            .body("names.size", is(5));
    }
}
