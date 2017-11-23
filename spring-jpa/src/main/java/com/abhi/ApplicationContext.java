package com.abhi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ApplicationContext {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationContext.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(ApplicationContext.class, args);
		Environment env = ctx.getEnvironment();
		logger.debug("Started Spring-JPA Application on "+ env.getProperty("server.port"));
	}
}