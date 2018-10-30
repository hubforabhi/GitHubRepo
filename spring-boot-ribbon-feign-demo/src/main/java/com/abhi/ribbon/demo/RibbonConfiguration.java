package com.abhi.ribbon.demo;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

@RibbonClients(value={
@RibbonClient(name="Order-Service", configuration = OrderServiceConfiguration.class),
@RibbonClient(name="Product-Service", configuration = ProductServiceConfiguration.class)})
public class RibbonConfiguration {
}
