////package com.backend.dtos;
////
////import com.backend.entity.Status;
////
////public class PatientByDoctorDto {
////
////    private Long patientId;
////    private Long userId;
////    private String firstname;
////    private String lastname;
////    private Status appointmentStatus;
////
////    // 🔴 REQUIRED: exact constructor for JPQL
////    public PatientByDoctorDto(
////            Long patientId,
////            Long userId,
////            String firstname,
////            String lastname,
////            Status appointmentStatus
////    ) {
////        this.patientId = patientId;
////        this.userId = userId;
////        this.firstname = firstname;
////        this.lastname = lastname;
////        this.appointmentStatus = appointmentStatus;
////    }
////
////    // getters (Lombok optional, but not required for JPQL)
////    public Long getPatientId() { return patientId; }
////    public Long getUserId() { return userId; }
////    public String getFirstname() { return firstname; }
////    public String getLastname() { return lastname; }
////    public Status getAppointmentStatus() { return appointmentStatus; }
////}
////
////
//
//
//package com.backend.dtos;
//
//import com.backend.entity.AppointmentStatus;
//
//public class PatientByDoctorDto {
//
//    private Long patientId;
//    private Long userId;
//    private String firstname;
//    private String lastname;
//    private AppointmentStatus appointmentStatus;
//
//    // REQUIRED for JPQL
//    public PatientByDoctorDto(
//            Long patientId,
//            Long userId,
//            String firstname,
//            String lastname,
//            AppointmentStatus appointmentStatus
//    ) {
//        this.patientId = patientId;
//        this.userId = userId;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.appointmentStatus = appointmentStatus;
//    }
//
//    public Long getPatientId() { return patientId; }
//    public Long getUserId() { return userId; }
//    public String getFirstname() { return firstname; }
//    public String getLastname() { return lastname; }
//    public AppointmentStatus getAppointmentStatus() { return appointmentStatus; }
//}


package com.backend.dtos;

//import java.time.LocalDateTime;
//
//import com.backend.entity.AppointmentStatus;
//import com.backend.entity.Gender;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//public class PatientByDoctorDto {
//
//    private Long patientId;
//    private String firstname;
//    private String lastname;
//    private String phone;
//    private Gender gender;
//    private LocalDateTime appointmentDate;
//    private AppointmentStatus appointmentStatus;
//}



import java.time.LocalDate;
import java.time.LocalDateTime;

import com.backend.entity.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientByDoctorDto {

    private String patientName;      // user.firstname + user.lastname
    private Gender gender;            // user.gender
    private LocalDate dob;            // user.dob
    private String phone;             // user.phone
    private String address;           // user.address
    private String medicalHistory;    // patient.medicalHistory
    private LocalDateTime admitDate;  // patient.admitDate
    private LocalDateTime dischargeDate; // patient.dischargeDate
}
