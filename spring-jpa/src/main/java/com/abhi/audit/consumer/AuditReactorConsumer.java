package com.abhi.audit.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abhi.audit.service.AuditService;

import reactor.bus.Event;
import reactor.fn.Consumer;

@Component
public class AuditReactorConsumer implements Consumer<Event<String>> {
	private static Logger logger = LoggerFactory.getLogger(AuditReactorConsumer.class);

	@Autowired
	private AuditService auditService;
	
	@Override
	public void accept(Event<String> e) {
		logger.debug("AuditReactorConsumer "+e.getData());
		auditService.insertAuditRecord();
	}
}