package com.abhi.spring.security.demo.domain.view;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class UserView {
	@NotNull
	@NotBlank	
	private String id;
	@NotNull(message="{UserView.fName.NotNull}")
	@Length(min=3, max=12, message="{UserView.fName.length}")
	private String fName;
	@NotNull(message="{UserView.lName.NotNull}")
	@Length(min=1, max=12, message="{UserView.fName.length}")	
	private String lName;
	
	public UserView() {
	}
	
	public UserView(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}
}
