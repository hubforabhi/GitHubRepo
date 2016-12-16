package com.abhi.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface RequestProcessorGateway {

	@Gateway(requestChannel="requestProcessorChannel")
	public void processRequest(String payload);
}
