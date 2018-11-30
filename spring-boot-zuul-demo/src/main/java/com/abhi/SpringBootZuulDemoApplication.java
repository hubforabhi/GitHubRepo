package com.abhi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Import;

import com.abhi.hystrix.HystrixConfiguration;
import com.abhi.zuul.ZuulConfiguration;

@SpringBootApplication
@Import({ZuulConfiguration.class, HystrixConfiguration.class})
public class SpringBootZuulDemoApplication {
	private static Logger logger = LoggerFactory.getLogger(SpringBootZuulDemoApplication.class);
    
	public static void main(String[] args) {
		SpringApplication.run(SpringBootZuulDemoApplication.class, args);
	}
}