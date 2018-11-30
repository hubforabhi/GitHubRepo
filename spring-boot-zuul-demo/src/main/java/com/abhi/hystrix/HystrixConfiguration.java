package com.abhi.hystrix;

import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
@EnableHystrixDashboard
@EnableTurbineStream
public class HystrixConfiguration {

}
