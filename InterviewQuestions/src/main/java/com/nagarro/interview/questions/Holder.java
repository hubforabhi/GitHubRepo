package com.nagarro.interview.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Holder {
	
	private Dependency1 d1;
	private Dependency2 d2;
	private Dependency3 d3;
	
	@Autowired	
	public Holder(Dependency1 d1, Dependency2 d2) {
		this.d1 = d1;
		this.d2 = d2;
		System.out.println("d1, d2");
	}
	
	@Autowired	
	public Holder(Dependency2 d2, Dependency3 d3) {
		this.d2 = d2;
		this.d3 = d3;
		System.out.println("d2, d3");
	}
	
	@Autowired	
	public Holder(Dependency1 d1, Dependency3 d3) {
		this.d1 = d1;
		this.d3 = d3;
		System.out.println("d1, d3");
	}
	
	@Autowired
	public Holder(Dependency1 d1, Dependency2 d2, Dependency3 d3) {
		this.d1 = d1;
		this.d2 = d2;
		this.d3 = d3;
		System.out.println("d1, d2, d3");
	}

}
