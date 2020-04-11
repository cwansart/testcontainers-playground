package de.cwansart.testcontainers.playground.reusable_container.application.name;

import org.junit.Before;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class NameResourceTest {

    private NameResource resource;

    @Before
    public void setUp() {
        this.resource = new NameResource();
    }

    @Test
    public void getNames() {

        JsonObject expectedResponse = Json.createObjectBuilder()
            .add("names", Json.createArrayBuilder(Arrays.asList(
                "Lea",
                "Christian",
                "Timo",
                "Christoph",
                "Jan"
            ))).build();

        Response response = this.resource.getNames();

        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        assertEquals(expectedResponse, response.getEntity());
    }
}
