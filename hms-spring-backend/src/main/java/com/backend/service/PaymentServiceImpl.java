package com.backend.service;




import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.backend.dtos.InvoiceDTO;
import com.backend.dtos.PaymentDTO;
import com.backend.dtos.PaymentRespDTO;
import com.backend.entity.Invoice;
import com.backend.entity.InvoiceStatus;
import com.backend.entity.Patient;
import com.backend.entity.Payment;

import com.backend.repository.InvoiceRepository;
import com.backend.repository.PatientRepository;
import com.backend.repository.PaymentRepository;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;
	

    @Override
    public Payment makePayment(PaymentDTO dto) {

        if (dto.getInvoiceId() == null) {
            throw new IllegalArgumentException("Invoice ID must not be null");
        }

        //  Invoice load
        Invoice invoice = invoiceRepository.findById(dto.getInvoiceId())
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        // Check status
        if (invoice.getStatus() == InvoiceStatus.PAID) {
            throw new RuntimeException("Invoice already paid");
        }

        // Get patient FROM invoice
        Patient patient = invoice.getPatient();
      
        // 4️⃣ Create payment
        Payment payment = new Payment();
        payment.setInvoice(invoice);
        payment.setPatient(patient);
        payment.setAmount(dto.getAmount());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setPaymentDate(LocalDate.now());

        // Update invoice status
        invoice.setStatus(InvoiceStatus.PAID);
        invoiceRepository.save(invoice);

        // Save payment
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getPaymentsByPatient(Long patientId) {
        return paymentRepository.findByInvoicePatientId(patientId);
    }

	@Override
	public   List<PaymentRespDTO>getAllPayments() {
		// TODO Auto-generated method stub
		 
			      return  paymentRepository.findAll().stream()
			            .map(p -> new PaymentRespDTO(
			                p.getId(),
			                p.getInvoice().getId(),
			                p.getPatient().getId(),
			                p.getPatient().getUser().getFirstname() + " " +
			                p.getPatient().getUser().getLastname(),
			                p.getAmount(),
			                p.getPaymentMethod(),
			                p.getPaymentDate()
			            ))
			            .toList();

			   
	}
}


