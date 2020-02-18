package com.abhi.order.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootAdminClientOrderServiceApplication {
	private static Logger logger = LoggerFactory.getLogger(SpringBootAdminClientOrderServiceApplication.class);
	
	@Autowired
	private ApplicationProperties appProperties;
	
	@Bean
	public CommandLineRunner setup() {
		return (args) -> {
			System.out.println("Properties ENV Name ----> "+appProperties.getEnv("name"));
		};
	}
    
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAdminClientOrderServiceApplication.class, args);
	}
}