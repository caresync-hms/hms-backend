//package com.backend.dtos;
//
//import java.time.LocalDateTime;
//import com.backend.entity.Status;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.NoArgsConstructor;
//import lombok.AllArgsConstructor;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class AppointmentBookingDto {
//
//    private Long patientId;
//    private String doctorName;
//    private String doctorSpecialization;
//    private LocalDateTime dateOfApp;
//    private Status status;
//
//}


package com.backend.dtos;

import java.time.LocalDateTime;
import com.backend.entity.AppointmentStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentBookingDto {

    private Long patientId;
    private String doctorName;
    private String doctorSpecialization;
    private LocalDateTime dateOfApp;
    private AppointmentStatus status;
}

