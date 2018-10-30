package com.abhi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abhi.feign.demo.FeignConfiguration;
import com.abhi.ribbon.demo.RibbonConfiguration;

@SpringBootApplication
@EnableDiscoveryClient
@Import({FeignConfiguration.class, RibbonConfiguration.class})
public class SpringBootRibbonFeignDemoApplication {
	private static Logger logger = LoggerFactory.getLogger(SpringBootRibbonFeignDemoApplication.class);
	
	@RequestMapping(value = "/ping")
	public ResponseEntity<String> ping() {
		logger.debug("SpringBootRibbonFeignDemoApplication.ping");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRibbonFeignDemoApplication.class, args);
	}
}