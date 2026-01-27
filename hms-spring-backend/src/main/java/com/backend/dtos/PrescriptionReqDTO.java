package com.backend.dtos;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class PrescriptionReqDTO {
	 private Long appointmentId;
	    private Long doctorId;
	    private Long patientId;
	    private String medicane ;
	    private LocalDateTime dateIssued;
	    private String notes;
}
