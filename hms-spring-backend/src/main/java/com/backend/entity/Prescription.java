package com.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity 
@AttributeOverride(
	    name = "UserId",
	    column = @Column(name = "prescription_id")
	)
public class Prescription extends Base {
@ManyToOne
private Appointment appointment ;
@ManyToOne
private Doctor doctor ;
@ManyToOne
private Patient patient ;
private LocalDateTime issueDate ;
private String advice ;
}
