package com.backend.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@JoinColumn(name = "issueDate")
private LocalDateTime issueDate ;
@JoinColumn(name = "advice")
private String advice ;
@JoinColumn(name = "medicine")
private String medicine ;
}
