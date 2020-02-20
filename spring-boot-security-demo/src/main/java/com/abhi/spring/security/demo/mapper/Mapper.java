package com.abhi.spring.security.demo.mapper;

public interface Mapper<I, O> {
	public O mapFrom (I i);
	public I mapTo (O o);
}
