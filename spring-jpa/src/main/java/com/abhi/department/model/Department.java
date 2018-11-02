package com.abhi.department.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.abhi.employee.model.Employee;

@Entity
@Table(name="department")
public class Department {

	@Id
	private Long id;
	
	@Column(name="dname", nullable=false, length=25)
	private String name;
	
	@OneToMany(mappedBy="department", fetch=FetchType.LAZY)
	private Set<Employee> empList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(Set<Employee> empList) {
		this.empList = empList;
	}
	
}
