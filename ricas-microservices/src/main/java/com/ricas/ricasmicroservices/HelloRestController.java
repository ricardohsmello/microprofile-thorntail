package com.ricas.ricasmicroservices;

import java.net.InetAddress;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@ConfigurationProperties(prefix = "helloapp")
public class HelloRestController {

	@RequestMapping(method = RequestMethod.GET, value = "/hello", produces = "text/plain")
	public String hello() {
		String hostname = null;
		
		try {
			hostname = InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			hostname = "unknow";
		}
		
		return saying + " " + hostname;
	}
	
	private String saying;

	public void setSaying(String saying) {
		this.saying = saying;
	}

}
