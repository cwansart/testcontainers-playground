package de.cwansart.testcontainers.playground.docker_healthcheck.application.healthcheck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("healthcheck")
@RequestScoped
public class HealthcheckResource {

    private static final Logger LOG = LoggerFactory.getLogger(HealthcheckResource.class);

    @GET
    public Response getHealth() {

        LOG.info("Calling HealthcheckResource.getHealth");

        return Response.noContent().build();
    }
}
