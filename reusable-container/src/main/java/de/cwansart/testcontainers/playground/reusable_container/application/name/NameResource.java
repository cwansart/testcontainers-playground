package de.cwansart.testcontainers.playground.reusable_container.application.name;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("names")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class NameResource {

    private static final Logger LOG = LoggerFactory.getLogger(NameResource.class);

    private static final List<String> NAMES = Arrays.asList(
        "Lea",
        "Christian",
        "Timo",
        "Christoph",
        "Jan"
    );

    @GET
    public Response getNames() {

        LOG.info("Calling NameResource.getNames");

        JsonObject jsonResponse = Json.createObjectBuilder()
            .add("names", Json.createArrayBuilder(NAMES)).build();

        return Response.ok(jsonResponse).build();
    }
}
