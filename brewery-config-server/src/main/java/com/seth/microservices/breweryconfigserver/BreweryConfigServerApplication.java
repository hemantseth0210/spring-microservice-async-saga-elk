package com.seth.microservices.breweryconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class BreweryConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BreweryConfigServerApplication.class, args);
	}

}
