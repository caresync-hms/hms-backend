package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.backend.entity.Appointment;

public interface AppointmentRepo  extends JpaRepository<Appointment, Long> {
	List<Appointment> findByDoctorId(Long doctorId);
}
