package com.backend.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "deptId"))
public class Department extends Base {
	@Column(name = "dept_name")
	private String departmentName;

	@Column(length = 100)
	private String description;
}
