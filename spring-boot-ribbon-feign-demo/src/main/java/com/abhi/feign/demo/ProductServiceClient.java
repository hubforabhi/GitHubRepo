package com.abhi.feign.demo;

import org.springframework.cloud.netflix.feign.FeignClient;

import feign.RequestLine;

@FeignClient(name = "product-service")
public interface ProductServiceClient {

	@RequestLine("GET")
    void ping();
}
