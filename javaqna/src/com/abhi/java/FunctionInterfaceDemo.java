package com.abhi.java;

@FunctionalInterface
interface DemoStaticReference {
	void demoStatic(int a);
}

@FunctionalInterface
interface DemoInstanceReference {
	void demoInstance(int a);
}

@FunctionalInterface
interface DemoConstructorReference {
	FunctionInterfaceDemo demoConstructor(int a);
}

//All higher level java core classes's method can't be declared in functional interface like this, hence need to remove declaration FunctionalInterface
//@FunctionalInterface
interface DemoEquals {
	public boolean equals(Object obj);  
}

public class FunctionInterfaceDemo {
	public FunctionInterfaceDemo(int a) {System.out.println("Constructor Reference" +a);}
	
	static void demoStatic(int a) {
		System.out.println(a);
	}
	
	void demoInstance(int a) {
		System.out.println(a);
	}
	
	static public void main(String... args) {
		//static method reference with functional interface
		DemoStaticReference staticRef = FunctionInterfaceDemo::demoStatic;
		staticRef.demoStatic(1);		
		
		//instance method reference with functional interface
		DemoInstanceReference  instRefe = new FunctionInterfaceDemo(2)::demoInstance;
		instRefe.demoInstance(3);
		
		//constructor method reference with functional interface		
		DemoConstructorReference conRef = FunctionInterfaceDemo::new; // see nothing is happening, no vairable is passed
		conRef.demoConstructor(12); // constructor referred from functional interface and parameter value is passed 
	}	
}