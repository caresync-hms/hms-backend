package com.backend.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverride(
	    name = "id",
	    column = @Column(name = "doctor_id")
	)
public class Doctor extends Base  {
	@OneToOne
	@JoinColumn(name="user_id")
	private User user ;
	@Column(name="specialization")
	private String specilization ;
	@ManyToOne
	@JoinColumn(name="dept_id")
	private Department dept ;
}
