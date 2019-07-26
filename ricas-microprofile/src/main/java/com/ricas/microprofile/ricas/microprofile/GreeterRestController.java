package com.ricas.microprofile.ricas.microprofile;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.opentracing.ClientTracingRegistrar;
import org.eclipse.microprofile.opentracing.Traced;


/**
 * @author ricardo.mello
 *
 */
@Path("/api")
public class GreeterRestController {

    @Inject
    @ConfigProperty(name="greeting.saying", defaultValue = "Hello")
    private String saying;

    @Inject
    @ConfigProperty(name = "greeting.backendServiceHost",  defaultValue = "localhost")
    private String backendServiceHost;

    @Inject
    @ConfigProperty(name = "greeting.backendServicePort", defaultValue = "8080")
    private int backendServicePort;


    @GET
    @Produces("text/plain")
    @Path("greeting")
    @CircuitBreaker
    @Timeout
    @Fallback(fallbackMethod = "fallback")
    @Traced
    public String greeting() {

        String backendServiceUrl = String.format("http://%s:%d",
                backendServiceHost,backendServicePort);

        System.out.println("Sending to: " + backendServiceUrl);

        Client client = ClientTracingRegistrar
                .configure(ClientBuilder.newBuilder()).build();

        BackendDTO backendDTO = client.target(backendServiceUrl)
                .path("api")
                .path("backend")
                .queryParam("greeting", saying)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(BackendDTO.class);

        return backendDTO.getGreeting()
                + " at host: " + backendDTO.getIp();
    }

    public String fallback(){
        return saying + " at host "  +
        System.getenv("HOSTNAME") + " - (fallback)";
    }

}
