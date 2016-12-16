package com.abhi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhi.service.CachedService;

import net.spy.memcached.MemcachedClient;

@Service
public class MemcachedServiceImpl implements CachedService {
	
	@Autowired
	private MemcachedClient memcachedClient;
	
	@Override
	public void set(String key, Object payload) {
		memcachedClient.add(key, 3600, payload);
	}
	
	@Override
	public void set(String key, int exp, Object payload) {
		memcachedClient.add(key, 3600, payload);
	}
	
	@Override
	public Object get(String key) {
		return memcachedClient.get(key);
	}
}
