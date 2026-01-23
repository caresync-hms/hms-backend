package com.backend.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
@AttributeOverride(
	    name = "id",
	    column = @Column(name = "deptId")
	)
public class Department extends Base  {
	@Column(name="dept_name")
	private String departmentName;
}
