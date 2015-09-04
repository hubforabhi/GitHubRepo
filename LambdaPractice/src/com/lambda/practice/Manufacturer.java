package com.lambda.practice;

import java.lang.annotation.Repeatable;

@Repeatable(value = Cars.class)
public @interface Manufacturer {
	String value();
}
