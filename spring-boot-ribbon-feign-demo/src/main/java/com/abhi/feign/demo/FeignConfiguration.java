package com.abhi.feign.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients
public class FeignConfiguration {
	private static Logger logger = LoggerFactory.getLogger(FeignConfiguration.class);
}
