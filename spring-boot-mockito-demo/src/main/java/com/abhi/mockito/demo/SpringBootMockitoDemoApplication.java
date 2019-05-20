package com.abhi.mockito.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.abhi.mockito.service.BusinessService;
import com.abhi.mockito.service.BusinessServiceImpl;
import com.abhi.mockito.service.DataService;
import com.abhi.mockito.service.DataServiceImpl;

@SpringBootApplication
public class SpringBootMockitoDemoApplication {
	@Bean
	public DataService dataService() {
		return new DataServiceImpl();
	}
	
	@Bean
	public BusinessService businessService() {
		return new BusinessServiceImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMockitoDemoApplication.class, args);
	}

}
