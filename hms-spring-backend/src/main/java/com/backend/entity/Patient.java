package com.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="patient")
@Getter
@Setter
@NoArgsConstructor

public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="patient_id")
	private Integer patientId;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private BaseUser user;
	@Column(name="admit_Date")
	private LocalDateTime admitDate;
	@Column(name="discharge_Date")
	private LocalDateTime dischargeDate;
}
