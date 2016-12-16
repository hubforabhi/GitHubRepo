package com.abhi.service;

public interface CachedService {
	public void set(String key, Object payload);
	public void set(String key, int exp, Object payload);
	public Object get(String key);	
}