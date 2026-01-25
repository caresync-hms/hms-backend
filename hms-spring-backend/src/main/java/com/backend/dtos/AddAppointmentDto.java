//package com.backend.dtos;
////
////import java.time.LocalDateTime;
////
////import com.backend.entity.Status;
////
////import lombok.AllArgsConstructor;
////import lombok.Data;
////import lombok.NoArgsConstructor;
////
////@Data
////@AllArgsConstructor
////@NoArgsConstructor
////
//
//import java.time.LocalDateTime;
//
//import com.backend.entity.Status;
//
//import jakarta.validation.constraints.NotNull;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class AddAppointmentDto {
//
//    @NotNull(message = "Doctor ID is required")
//    private Long doctorId;
//
//    @NotNull(message = "Patient ID is required")
//    private Long patientId;
//
//    @NotNull(message = "Appointment date is required")
//    private LocalDateTime dateOfAppointment;
//
//    @NotNull(message = "Status is required")
//    private Status status;
//}
//
////public class AddAppointmentDto {
////
////    private Long doctorId;        // doctor reference
////    private Long patientId;       // patient reference
////    private LocalDateTime dateOfAppointment;
////    private Status status;
////}
////
