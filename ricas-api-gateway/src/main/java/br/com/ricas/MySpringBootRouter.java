package br.com.ricas;

import java.util.ArrayList;

import org.apache.camel.builder.AggregationStrategies;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ricardo.mello
 *
 */
@Component
@ConfigurationProperties(prefix="gateway")
public class MySpringBootRouter extends RouteBuilder {

	private static final String REST_ENDPOINT = "http4:%s/api/greeting?httpClient.connectTimeout=1000"
			+ "&bridgeEndpoint=true" + "&copyHeaders=true" + "&connectionClose=true";
	
    private String springbootsvcurl, microprofilesvcurl;

	@Override
	public void configure() {
		from("direct:ricas-microprofile").streamCaching()
        	    .toF(REST_ENDPOINT, microprofilesvcurl)
        	    .log("Response from Microprofile microservice: " +
        	    		"${body}")
        	    .convertBodyTo(String.class)
        	    .end();


		from("direct:ricas-microservices").streamCaching()
				.toF(REST_ENDPOINT, springbootsvcurl)
				.log("Response from Spring Boot microservice: " + 
						"${body}")
				.convertBodyTo(String.class)
				.end();

        rest()
        	.get("/gateway").enableCORS(true)
        	.route()
            	.multicast(AggregationStrategies.flexible()
                    .accumulateInCollection(ArrayList.class))
            	.parallelProcessing()
                	.to("direct:ricas-microprofile")
                	.to("direct:ricas-microservices")
                .end()
            .marshal().json(JsonLibrary.Jackson)
            .convertBodyTo(String.class)
        .endRest();
        
	}
	
    public void setMicroprofilesvcurl(String microprofilesvcurl) {
        this.microprofilesvcurl = microprofilesvcurl;
    }

    public void setSpringbootsvcurl(String springbootsvcurl) {
        this.springbootsvcurl = springbootsvcurl;
    }

}
