package com.backend.dtos;

import java.time.LocalDateTime;
import com.backend.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminAppointmentDTO {

    private String doctorName;
    private String patientName;
    private LocalDateTime appointmentDate;
    private Status appointmentStatus;
    private String doctorDepartment;
}
