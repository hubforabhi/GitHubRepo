package com.abhi.feign.demo;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;

import feign.RequestLine;

import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "product-service")
public interface ProductServiceClient {

	//@RequestLine("GET") // Annotation is needed if Feign.builder is used to create Feign client proxy
	@RequestMapping("/product/ping")
    void ping();
	
	//@RequestLine("GET")
	@RequestMapping("/product/find")
    String find();
	
	//@RequestLine("GET")
	@RequestMapping("/product/findAll")
    List<String> findAll();
}