package com.backend.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionRespDTO {

	private Long prescriptionId;
	private Long patientId;
	private String patientName;
	private Long doctorId;
	private String doctorName;
	private String medicine;
	private Long appointmentId;
	private LocalDateTime dateIssued;
	private String notes;

}
