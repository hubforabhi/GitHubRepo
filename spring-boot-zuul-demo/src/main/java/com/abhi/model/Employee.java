package com.abhi.model;

public class Employee {
	
	private boolean manager;
	
	public Employee() {	
		manager = true;
	}

	public boolean isManager() {
		return manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}

}
