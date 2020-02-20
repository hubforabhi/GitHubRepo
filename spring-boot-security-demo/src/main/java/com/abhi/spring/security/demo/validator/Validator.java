package com.abhi.spring.security.demo.validator;

public interface Validator<T> {
	public boolean validate(T t);
}
