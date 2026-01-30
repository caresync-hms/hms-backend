package com.backend.service;



import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.custom_exceptions.ResourceNotFoundException;
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
    public InvoiceRespDTO createInvoice(InvoiceDTO dto) {

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Invoice invoice = new Invoice();
        invoice.setPatient(patient);
        invoice.setTotalAmount(dto.getAmount());
        invoice.setStatus(InvoiceStatus.PENDING);
        invoice.setCreatedDate(LocalDate.now());

        Invoice saved = invoiceRepository.save(invoice);

        return mapToDTO(saved);
    }

    @Override
    public InvoiceRespDTO getInvoiceById(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        return mapToDTO(invoice);
    }

    @Override
    public List<InvoiceRespDTO> getInvoicesByPatient(Long patientId) {
        return invoiceRepository.findByPatient_Id(patientId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public List<InvoiceRespDTO> getAllInvoices() {
        return invoiceRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // ✅ Centralized mapper
    private InvoiceRespDTO mapToDTO(Invoice inv) {
        return new InvoiceRespDTO(
                inv.getId(),
                inv.getPatient().getId(),
                inv.getPatient().getUser().getFirstname() + " " +
                inv.getPatient().getUser().getLastname(),
                inv.getTotalAmount(),
                inv.getStatus().name(),
                inv.getCreatedDate()
        );
    }
    
    @Override
    public InvoiceRespDTO updateStatus(Long invoiceId, InvoiceStatus status) {

        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Invoice not found with id: " + invoiceId
                        )
                );

        invoice.setStatus(status);

        // optional: auto set paid date
        if (status == InvoiceStatus.PAID) {
            invoice.setCreatedDate(LocalDate.now());
        }

        Invoice savedInvoice = invoiceRepository.save(invoice);

        return mapToDTO(savedInvoice);
    }
   


}




