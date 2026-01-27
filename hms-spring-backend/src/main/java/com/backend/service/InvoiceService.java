package com.backend.service;



import java.util.List;

import com.backend.dtos.InvoiceDTO;
import com.backend.dtos.InvoiceRespDTO;
import com.backend.entity.Invoice;

public interface InvoiceService {

	    Invoice createInvoice(InvoiceDTO dto);

	    Invoice getInvoiceById(Long invoiceId);

	    List<Invoice> getInvoicesByPatient(Long patientId);
	    List<InvoiceRespDTO> getAllInvoices();
	}


