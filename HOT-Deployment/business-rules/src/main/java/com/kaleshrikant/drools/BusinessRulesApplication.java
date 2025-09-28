package com.kaleshrikant.drools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.kaleshrikant.drools")
public class BusinessRulesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinessRulesApplication.class, args);
	}

}
