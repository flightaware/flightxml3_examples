package com.flightaware.examples;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.flightaware.examples.handlers.FlightxmlErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Flightxml3WithSpringApplication {

	private static final Logger log = LoggerFactory.getLogger(Flightxml3WithSpringApplication.class);

	@Autowired
	FlightxmlErrorHandler flightxmlErrorHandler;

	@Autowired
	Environment env;

	public static void main(String[] args) {
		SpringApplication.run(Flightxml3WithSpringApplication.class, args);
	}

	@Bean(name = "flightxmlRestTemplate")
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.basicAuthorization(env.getProperty("fxml3.username"), env.getProperty("fxml3.password")).errorHandler(flightxmlErrorHandler).build();
	}

	@Bean
	public ObjectMapper jacksonObjectMapper() {
		return new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
	}

}
