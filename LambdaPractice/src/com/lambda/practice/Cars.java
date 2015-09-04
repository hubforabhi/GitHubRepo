package com.lambda.practice;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention( RetentionPolicy.RUNTIME )
public @interface Cars {
	
	Manufacturer[] value() default{};
}
