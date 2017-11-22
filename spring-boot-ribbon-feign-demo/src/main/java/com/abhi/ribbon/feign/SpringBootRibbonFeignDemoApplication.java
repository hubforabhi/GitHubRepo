package com.abhi.ribbon.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@RequestMapping("/ribbon")
@SuppressWarnings("rawtypes")
@RibbonClients(value={
@RibbonClient(name="Order-Service", configuration = OrderServiceConfiguration.class),
@RibbonClient(name="Product-Service", configuration = ProductServiceConfiguration.class)})
public class SpringBootRibbonFeignDemoApplication {
	private static Logger logger = LoggerFactory.getLogger(SpringBootRibbonFeignDemoApplication.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private LoadBalancerClient client;

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
	@RequestMapping(value = "/order")
	public ResponseEntity<String> order() {		
		ServiceInstance instance = client.choose("Order-Service");
		logger.debug("SpringBootRibbonFeignDemoApplication.order status : " +
		instance !=null? instance.getHost(): "INSTANCE NOT FOUND");
		ResponseEntity<?> entity = restTemplate.getForEntity("http://Order-Service/order/ping", ResponseEntity.class);
		logger.debug("SpringBootRibbonFeignDemoApplication.order status : " +entity.getStatusCodeValue());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/product")
	public ResponseEntity<String> product() {
		ServiceInstance instance = client.choose("Product-Service");
		logger.debug("SpringBootRibbonFeignDemoApplication.product status : " +
		instance !=null? instance.getHost(): "INSTANCE NOT FOUND");
		ResponseEntity<?> entity = restTemplate.getForEntity("http://Product-Service/product/ping", ResponseEntity.class);
		logger.debug("SpringBootRibbonFeignDemoApplication.product status : " +entity.getStatusCodeValue());
		return new ResponseEntity<>(HttpStatus.OK);
	}
    
	@RequestMapping(value = "/ping")
	public ResponseEntity<String> ping() {
		logger.debug("SpringBootRibbonFeignDemoApplication.ping");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRibbonFeignDemoApplication.class, args);
	}
}