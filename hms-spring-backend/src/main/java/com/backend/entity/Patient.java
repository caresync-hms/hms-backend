package com.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "patient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@AttributeOverride(name = "id", column = @Column(name = "patient_id"))

public class Patient extends Base {

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false, unique = true)
	private User user;
	@Enumerated(EnumType.STRING)
	@Column(name = "bloodGroup")
	private BloodGroup bloodGroup;
	@Column(name = "Medical_history", length = 500)
	private String medicalHistory;
	@Column(name = "admit_Date")
	private LocalDateTime admitDate;
	@Column(name = "discharge_Date")
	private LocalDateTime dischargeDate;

}
