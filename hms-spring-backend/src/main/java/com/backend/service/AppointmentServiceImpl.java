package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.dtos.AddAppointmentDto;
import com.backend.dtos.AppointmentBookingDto;
import com.backend.dtos.AppointmentBookingRequestDto;
import com.backend.dtos.AppointmentByPatientDto;
import com.backend.dtos.AppointmentUpdateRequestDto;
import com.backend.dtos.PatientByDoctorDto;
import com.backend.entity.Appointment;
import com.backend.entity.Doctor;
import com.backend.entity.Patient;
import com.backend.entity.Status;
import com.backend.entity.User;
import com.backend.repository.AppointmentRepo;
import com.backend.repository.DoctorRepo;
import com.backend.repository.PatientRepository;
import com.backend.service.AppointmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
		@Autowired
    private  AppointmentRepo appointmentRepository;
		@Autowired
    private  DoctorRepo doctorRepository;
		@Autowired
    private  PatientRepository patientRepository;

    @Override
    public Appointment addAppointment(AddAppointmentDto dto) {

        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setDateOfAppointment(dto.getDateOfAppointment());
        appointment.setStatus(dto.getStatus());

        return appointmentRepository.save(appointment);
    }

//getpatientbydoctorid
    
    @Override
    public List<PatientByDoctorDto> getPatientsByDoctorId(Long doctorId) {
        return appointmentRepository.getPatientsByDoctorId(doctorId);
    }
    
   //getallappointmentbypatient
    @Override
    public List<AppointmentByPatientDto> getAllAppointmentsByPatientId(Long patientId) {
        return appointmentRepository.getAppointmentsByPatientId(patientId);
    }
    
    //booking
    @Override
    public AppointmentBookingDto bookAppointment(AppointmentBookingRequestDto dto) {

        // 1️⃣ Fetch doctor
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        // 2️⃣ Fetch patient
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // 3️⃣ Check availability
        boolean isBooked = appointmentRepository.existsByDoctor_IdAndDateOfAppointment(
                dto.getDoctorId(), dto.getDateOfAppointment()
        );
        if (isBooked) {
            throw new RuntimeException("Doctor is not available at this time");
        }

        // 4️⃣ Save appointment
        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setDateOfAppointment(dto.getDateOfAppointment());
        appointment.setStatus(Status.ACTIVE);

        Appointment saved = appointmentRepository.save(appointment);

        // 5️⃣ Map to response DTO
        AppointmentBookingDto response = new AppointmentBookingDto();
        response.setPatientId(saved.getPatient().getId());
        response.setDoctorName(saved.getDoctor().getUser().getFirstname() + " " + saved.getDoctor().getUser().getLastname());
        response.setDoctorSpecialization(saved.getDoctor().getSpecilization());
        response.setDateOfApp(saved.getDateOfAppointment());
        response.setStatus(saved.getStatus());

        return response;
    }
    
    //delete appoinntment
    @Override
    public void softDeleteAppointment(Long appointmentId) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        
        appointment.setStatus(Status.INACTIVE);

        appointmentRepository.save(appointment);
    }
    
    
    @Override
    public AppointmentBookingDto updateAppointmentDate(AppointmentUpdateRequestDto dto) {

        Appointment appointment = appointmentRepository.findById(dto.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        // ❌ Cannot update cancelled / blocked appointment
        if (appointment.getStatus() == Status.INACTIVE || appointment.getStatus() == Status.BLOCKED) {
            throw new RuntimeException("Cannot update this appointment");
        }

        // 🔁 Check doctor availability for new date
        boolean isBooked = appointmentRepository
                .existsByDoctor_IdAndDateOfAppointment(
                        appointment.getDoctor().getId(),
                        dto.getDateOfAppointment()
                );

        if (isBooked) {
            throw new RuntimeException("Doctor not available at this time");
        }

        // ✅ Update only date
        appointment.setDateOfAppointment(dto.getDateOfAppointment());

        Appointment updated = appointmentRepository.save(appointment);

        // 🔁 Build response
        AppointmentBookingDto response = new AppointmentBookingDto();
        response.setPatientId(updated.getPatient().getId());
        response.setDoctorSpecialization(updated.getDoctor().getSpecilization());
        response.setDateOfApp(updated.getDateOfAppointment());
        response.setStatus(updated.getStatus());

        User user = updated.getDoctor().getUser();
        if (user != null) {
            response.setDoctorName(user.getFirstname() + " " + user.getLastname());
        }

        return response;
    }

    
}
