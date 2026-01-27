package com.backend.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
	 List<Invoice> findByPatient_Id(Long patientId);
	 
}



