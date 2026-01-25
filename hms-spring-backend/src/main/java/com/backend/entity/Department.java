package com.backend.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverride(
	    name = "id",
	    column = @Column(name = "deptId")
	)
public class Department extends Base  {
	@Column(name="dept_name")
	private String departmentName;
	@Column(name = "description", length = 255)
    private String description;
}
