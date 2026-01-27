//
//package com.backend.dtos;
//
//import java.time.LocalDateTime;
//
//import com.backend.entity.AppointmentStatus;
//import com.backend.entity.Gender;
//
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//public class AppointmentResponseDto {
//	
//    private LocalDateTime dateOfAppointment;
//    private AppointmentStatus appointmentStatus;
//    private String patientName;
//    private String phoneNo;
//    private Gender gender;
//
//   
//    public AppointmentResponseDto(
//            LocalDateTime dateOfAppointment,
//            AppointmentStatus appointmentStatus,
//            String patientName,
//            String phoneNo,
//            Gender gender
//    ) {
//        this.dateOfAppointment = dateOfAppointment;
//        this.appointmentStatus = appointmentStatus;
//        this.patientName = patientName;
//        this.phoneNo = phoneNo;
//        this.gender = gender;
//    }
//}
//

package com.backend.dtos;

import java.time.LocalDateTime;

import com.backend.entity.AppointmentStatus;
import com.backend.entity.Gender;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentResponseDto {

    private Long appointmentId;   // ✅ added
    private LocalDateTime dateOfAppointment;
    private AppointmentStatus appointmentStatus;
    private String patientName;
    private String phoneNo;
    private Gender gender;

    public AppointmentResponseDto(
            Long appointmentId,
            LocalDateTime dateOfAppointment,
            AppointmentStatus appointmentStatus,
            String patientName,
            String phoneNo,
            Gender gender
    ) {
        this.appointmentId = appointmentId;
        this.dateOfAppointment = dateOfAppointment;
        this.appointmentStatus = appointmentStatus;
        this.patientName = patientName;
        this.phoneNo = phoneNo;
        this.gender = gender;
    }
}
