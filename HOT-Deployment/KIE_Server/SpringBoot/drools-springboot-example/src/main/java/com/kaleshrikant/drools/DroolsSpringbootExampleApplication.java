package com.kaleshrikant.drools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DroolsSpringbootExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroolsSpringbootExampleApplication.class, args);
		System.out.println("=== Drools Spring Boot Application Started ===");
		System.out.println("Rules organized in packages: rules.applicant, rules.loan");
		System.out.println("API available at: http://localhost:8090/api/rules");
	}
}
