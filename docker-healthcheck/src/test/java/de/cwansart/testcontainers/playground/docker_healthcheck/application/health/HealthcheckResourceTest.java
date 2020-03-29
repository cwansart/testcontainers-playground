package de.cwansart.testcontainers.playground.docker_healthcheck.application.health;

import de.cwansart.testcontainers.playground.docker_healthcheck.application.healthcheck.HealthcheckResource;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class HealthcheckResourceTest {

    private HealthcheckResource resource;

    @Before
    public void setUp() {
        this.resource = new HealthcheckResource();
    }

    @Test
    public void getHealth() {

        Response response = this.resource.getHealth();

        assertEquals(response.getStatus(), Response.Status.NO_CONTENT.getStatusCode());
    }
}
