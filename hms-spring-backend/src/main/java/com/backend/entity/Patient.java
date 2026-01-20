package com.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="patient")
@AttributeOverride(
	    name = "userId",
	    column = @Column(name = "patient_id")
	)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends BaseUser {
	
	@Column(name="admit_Date")
	private LocalDateTime admitDate;
	@Column(name="discharge_Date")
	private LocalDateTime dischargeDate;
}
