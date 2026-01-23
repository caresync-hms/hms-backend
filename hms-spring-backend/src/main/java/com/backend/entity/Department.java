package com.backend.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
@AttributeOverride(
	    name = "userID",
	    column = @Column(name = "dept_id")
	)
public class Department extends Base  {
	@Column(name="dept_name")
	private String departmentName;
}
