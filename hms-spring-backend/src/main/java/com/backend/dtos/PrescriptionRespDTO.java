package com.backend.dtos;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class PrescriptionRespDTO {
    private Long prescriptionId;
    private Long appointmentId;
    private Long doctorId;
    private Long patientId;
    private LocalDateTime dateIssued;
    private String notes;
}
