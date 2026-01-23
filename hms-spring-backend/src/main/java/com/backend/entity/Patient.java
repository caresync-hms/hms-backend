package com.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="patient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@AttributeOverride(
	    name = "UserId",
	    column = @Column(name = "patient_id")
	)

public class Patient extends Base  {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "patient_id")
//	private Long patientId;
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false, unique = true)
	private User user;
	@Enumerated(EnumType.STRING)
	@Column(name="bloodGroup")
	private BloodGroup bloodGroup;
	@Column(name = "Medical_history", length = 500)
	private String medicalHistory;
	@Column(name="admit_Date")
	private LocalDateTime admitDate;
	@Column(name="discharge_Date")
	private LocalDateTime dischargeDate;
	
}
