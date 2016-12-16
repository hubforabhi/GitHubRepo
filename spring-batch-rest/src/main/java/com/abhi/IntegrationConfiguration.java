package com.abhi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;

@Configuration
@EnableIntegration
@IntegrationComponentScan("com.abhi.gateway")
public class IntegrationConfiguration {

	@Bean(name="requestProcessorChannel")
	public DirectChannel requestProcessorChannel() {
		return new DirectChannel();
	}
	
}