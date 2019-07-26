package com.ricas.microprofile.ricas.microprofile;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 */
@Path("/hello")
public class HelloWorldEndpoint {

    @GET
    @Produces("text/plain")
    public Response doGet() {
    	return Response.ok("Hello Ricas Microprofile from Thorntail!").build();
    }
}



