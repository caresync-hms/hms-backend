package com.backend.dtos;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentBookingRequestDto {

    private Long doctorId;
    private Long patientId;
    private LocalDateTime dateOfAppointment;

}
