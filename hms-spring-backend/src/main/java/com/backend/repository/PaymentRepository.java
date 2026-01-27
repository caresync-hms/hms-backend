package com.backend.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    //List<Payment> findByPatient_Id(Long patientId);
    List<Payment> findByInvoicePatientId(Long patientId);

}


