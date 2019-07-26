package com.ricas.microprofile.ricas.microprofile;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.eclipse.microprofile.config.inject.ConfigProperty;


/**
 * @author ricardo.mello
 *
 */
@Path("/api")
public class HelloRestController {

    @Inject
    @ConfigProperty(name="helloapp.saying")
    private String saying;

    @GET
    @Produces("text/plain")
    @Path("/hello")
    public String hello() {
        String hostname = null;
        try {
            hostname = InetAddress.getLocalHost()
                    .getHostAddress();
        } catch (UnknownHostException e) {
            hostname = "unknown";
        }
        return saying + " " + hostname;
    }

}
