package com.abhi.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import reactor.bus.Event;
import reactor.bus.EventBus;

@MessageEndpoint
public class RequestProcessorGatewayServiceImpl {
	private static Logger logger = LoggerFactory.getLogger(RequestProcessorGatewayServiceImpl.class);
	
	@Autowired
	private EventBus eventBus;

	@ServiceActivator(inputChannel="requestProcessorChannel")
	public void processRequest(String payload) {
		logger.debug("IntegrationGatewayService Payload "+payload);
		eventBus.notify("BatchRequestEvent", Event.wrap(payload));
	}
}