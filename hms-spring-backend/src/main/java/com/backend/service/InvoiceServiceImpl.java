package com.backend.service;



import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.dtos.InvoiceDTO;
import com.backend.dtos.InvoiceRespDTO;
import com.backend.entity.Invoice;
import com.backend.entity.InvoiceStatus;
import com.backend.entity.Patient;
import com.backend.repository.InvoiceRepository;
import com.backend.repository.PatientRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;



	@Service
	@RequiredArgsConstructor
	@Transactional
	public class InvoiceServiceImpl implements InvoiceService {

	    private final InvoiceRepository invoiceRepository;
	    private final PatientRepository patientRepository;

	    @Override
	    public Invoice createInvoice(InvoiceDTO dto) {

	        Patient patient = patientRepository.findById(dto.getPatientId())
	                .orElseThrow(() -> new RuntimeException("Patient not found"));

	        Invoice invoice = new Invoice();
	        invoice.setPatient(patient);
	        invoice.setTotalAmount(dto.getAmount());
	        invoice.setStatus(InvoiceStatus.PENDING);
	        invoice.setCreatedDate(LocalDate.now());

	        return invoiceRepository.save(invoice);
	    }

	    @Override
	    public Invoice getInvoiceById(Long invoiceId) {
	        return invoiceRepository.findById(invoiceId)
	                .orElseThrow(() -> new RuntimeException("Invoice not found"));
	    }

	    @Override
	    public List<Invoice> getInvoicesByPatient(Long patientId) {
	        return invoiceRepository.findByPatient_Id(patientId);
	    }
	    
	    @Override
	    public List<InvoiceRespDTO> getAllInvoices() {
	        return invoiceRepository.findAll().stream()
	            .map(inv -> new InvoiceRespDTO(
	                inv.getId(),
	                inv.getPatient().getId(),
	                inv.getPatient().getUser().getFirstname()
	                  + " " +
	                  inv.getPatient().getUser().getLastname(),
	                inv.getTotalAmount(),
	                inv.getStatus().name(),
	                inv.getCreatedDate()
	            ))
	            .toList();
	    }
		}
	



