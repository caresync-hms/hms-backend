package com.backend.service;



import java.util.List;

import com.backend.dtos.InvoiceDTO;
import com.backend.dtos.InvoiceRespDTO;
import com.backend.entity.Invoice;





public interface InvoiceService {

    // Create invoice
    InvoiceRespDTO createInvoice(InvoiceDTO dto);

    // Get invoice by ID
    InvoiceRespDTO getInvoiceById(Long invoiceId);

    // Get invoices of a patient
    List<InvoiceRespDTO> getInvoicesByPatient(Long patientId);

    // Get all invoices
    List<InvoiceRespDTO> getAllInvoices();
}


