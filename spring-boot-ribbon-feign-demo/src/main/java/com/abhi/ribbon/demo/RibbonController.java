package com.abhi.ribbon.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.client.discovery.DiscoveryClient;

@RestController
@RequestMapping("/ribbon")
public class RibbonController {
	private static Logger logger = LoggerFactory.getLogger(RibbonController.class);
	
	@Autowired
	private DiscoveryClient discoveryClient;
	 
	@Autowired
	private LoadBalancerClient client;

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @Autowired
    private RestTemplate restTemplate;
    
	@RequestMapping(value = "/order")
	public ResponseEntity<String> order() {		
		ServiceInstance instance = client.choose("order-service");
		logger.debug("SpringBootRibbonFeignDemoApplication.order status : " +
		instance !=null? instance.getHost(): "INSTANCE NOT FOUND");
		ResponseEntity<?> entity = restTemplate.getForEntity("http://order-service/order/ping", ResponseEntity.class);
		logger.debug("SpringBootRibbonFeignDemoApplication.order status : " +entity.getStatusCodeValue());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/product")
	public ResponseEntity<String> product() {
		ServiceInstance instance = client.choose("product-service");
		logger.debug("SpringBootRibbonFeignDemoApplication.product status : " +
		instance !=null? instance.getHost(): "INSTANCE NOT FOUND");
		ResponseEntity<?> entity = restTemplate.getForEntity("http://product-service/product/ping", ResponseEntity.class);
		logger.debug("SpringBootRibbonFeignDemoApplication.product status : " +entity.getStatusCodeValue());
		
		List<ServiceInstance> instances = this.discoveryClient.getInstances("product-service");
        for (ServiceInstance serviceInstance : instances) {
        	logger.debug(serviceInstance.getUri().getPath());
        }
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
