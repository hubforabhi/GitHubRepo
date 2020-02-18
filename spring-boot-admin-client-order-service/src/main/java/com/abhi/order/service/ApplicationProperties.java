package com.abhi.order.service;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="application")
public class ApplicationProperties {
	private Map<String,String> env;
	
	public void setEnv(Map<String,String> env) {
		this.env = env;
	}
	
	public String getEnv(String k) {
		return env.get(k);
	}
}