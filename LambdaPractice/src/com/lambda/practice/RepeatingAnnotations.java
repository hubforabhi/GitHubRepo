package com.lambda.practice;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class RepeatingAnnotations {
	
	public static void main(String []args) {
		System.out.println(Objects.isNull(null));
		
		Manufacturer[] mfars = Car.class.getAnnotationsByType(Manufacturer.class);
		System.out.println(mfars.length);
		
		Cars cars = Car.class.getAnnotation(Cars.class);
		Consumer<Manufacturer> cons =  RepeatingAnnotations::printManuacturerNames;
		for(Manufacturer mfar :cars.value()) {
			cons.accept(mfar);
			printManuacturerNames(()-> mfar);
		}
	}
	
	static void printManuacturerNames(Manufacturer mfar) {
		System.out.println( mfar.value());
	}
	
	static void printManuacturerNames(Supplier<Manufacturer> mfar) {
		System.out.println(mfar.get().value());
	}	
	
}
