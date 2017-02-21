package com.abhi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableTransactionManagement
@Configuration
@ComponentScan(basePackages = "com.abhi.controller")
public class ApplicationContext {
	private static final Logger logger = LogManager.getLogger(ApplicationContext.class);

}