package com.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity 
@AttributeOverride(
	    name = "id",
	    column = @Column(name = "app_id")
	)
public class Appointment extends Base  {
@ManyToOne
@JoinColumn(name="doctor_id")
private Doctor doctor ;
@ManyToOne
@JoinColumn(name="patient_id")
private Patient patient ;
@Column(name="date_of_app")
private LocalDateTime dateOfAppointment;
@Enumerated(EnumType.STRING)
@Column(name="status")
private Status status ;
}
