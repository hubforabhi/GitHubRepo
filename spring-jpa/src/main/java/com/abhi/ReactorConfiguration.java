package com.abhi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.abhi.audit.consumer.AuditReactorConsumer;

import reactor.bus.EventBus;
import reactor.spring.context.config.EnableReactor;
import static reactor.bus.selector.Selectors.$;

@Configuration
@EnableReactor
public class ReactorConfiguration implements CommandLineRunner {
	private static Logger logger = LoggerFactory.getLogger(ReactorConfiguration.class);
	
	@Autowired
	private EventBus bus;
	
	@Autowired
	private AuditReactorConsumer consumer;
	
	@Override
	public void run(String... arg0) throws Exception {
		bus.on($("doAudit"), consumer);
	}
}
