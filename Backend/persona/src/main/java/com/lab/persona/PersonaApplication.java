package com.lab.persona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class PersonaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonaApplication.class, args);
	}

}
