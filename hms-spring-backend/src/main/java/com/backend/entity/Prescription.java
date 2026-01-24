package com.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity 
@AttributeOverride(
	    name = "id",
	    column = @Column(name = "presc_id")
	)
public class Prescription extends Base {
@ManyToOne
@JoinColumn(name = "appointment_id")
private Appointment appointment ;
@ManyToOne
@JoinColumn(name = "doctor_id")
private Doctor doctor ;
@ManyToOne
@JoinColumn(name = "patient_id")
private Patient patient ;
private LocalDateTime issueDate ;
private String advice ;
}
