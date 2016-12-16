package com.abhi.util;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class RestTemplateBase extends RestTemplate {
	
	private HttpRequestFactoryBase rfBase;
	
	public RestTemplateBase() {
		rfBase = new HttpRequestFactoryBase();
	}
	
	@Override
	public void setRequestFactory(ClientHttpRequestFactory requestFactory) {
		super.setRequestFactory(rfBase);
	}
	
	
}