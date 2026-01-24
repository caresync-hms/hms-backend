package com.backend.dtos;

import com.backend.entity.Status;

public class PatientByDoctorDto {

    private Long patientId;
    private Long userId;
    private String firstname;
    private String lastname;
    private Status appointmentStatus;

    // 🔴 REQUIRED: exact constructor for JPQL
    public PatientByDoctorDto(
            Long patientId,
            Long userId,
            String firstname,
            String lastname,
            Status appointmentStatus
    ) {
        this.patientId = patientId;
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.appointmentStatus = appointmentStatus;
    }

    // getters (Lombok optional, but not required for JPQL)
    public Long getPatientId() { return patientId; }
    public Long getUserId() { return userId; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public Status getAppointmentStatus() { return appointmentStatus; }
}
