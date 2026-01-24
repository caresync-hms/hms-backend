
package com.backend.dtos;

import com.backend.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentByPatientDto {

    private String doctorName;
    private String doctorSpecialization;
    private LocalDateTime dateOfAppointment;
    private Status appointmentStatus;
}
