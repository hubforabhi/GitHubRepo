package com.abhi;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.spy.memcached.MemcachedClient;

@Configuration
public class CacheConfiguration {

	@Bean
	public MemcachedClient memcachedClient() throws IOException {
		return new MemcachedClient(new InetSocketAddress("localhost", 11211));
	}
}
