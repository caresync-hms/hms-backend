package com.backend.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.backend.entity.Prescription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionRespDTO {

	private Long prescriptionId;

    private Long patientId;
    private String patientName;
    private String medicine ;
    private Long doctorId;
    private String doctorName;

    private Long appointmentId;

    private LocalDateTime dateIssued;
    private String notes;
    
    public PrescriptionRespDTO(Prescription p) {
        this.prescriptionId = p.getId();
        this.appointmentId = p.getAppointment().getId();
        this.doctorId = p.getDoctor().getId();
        this.patientId = p.getPatient().getId();
        this.dateIssued = p.getIssueDate();
        this.notes = p.getAdvice();
        this.medicine=p.getMedicine();
    }

}
