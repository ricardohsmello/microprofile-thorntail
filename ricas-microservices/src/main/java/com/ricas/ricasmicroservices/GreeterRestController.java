package com.ricas.ricasmicroservices;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ricardo.mello
 *
 */
@RestController
@RequestMapping("/api")
@ConfigurationProperties(prefix = "greeting")
public class GreeterRestController {

    private RestTemplate template = new RestTemplateBuilder().build();
	
	private String saying, backendServiceHost;

	private int backendServicePort;

	@RequestMapping(value = "/greeting", method = RequestMethod.GET, produces = "text/plain")
	public String greeting() {
		String backendServiceUrl = String.format("http://%s:%d/api/backend?greeting={greeting}", backendServiceHost,
				backendServicePort);

		System.out.println("Sending to " + backendServiceUrl);
		
		BackendDTO response = template.getForObject(backendServiceUrl, BackendDTO.class, saying);
		
		return response.getGreeting() + " at host: " + response.getIp();
	}

	public void setSaying(String saying) {
		this.saying = saying;
	}

	public void setBackendServiceHost(String backendServiceHost) {
		this.backendServiceHost = backendServiceHost;
	}

	public void setBackendServicePort(int backendServicePort) {
		this.backendServicePort = backendServicePort;
	}

}
