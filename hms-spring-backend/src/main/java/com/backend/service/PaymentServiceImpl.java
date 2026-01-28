package com.backend.service;




import java.io.ByteArrayOutputStream;
import java.time.LocalDate;

/*import java.time.LocalDate;
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
}*/



import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.dtos.PaymentDTO;
import com.backend.dtos.PaymentRespDTO;
import com.backend.entity.Invoice;
import com.backend.entity.InvoiceStatus;
import com.backend.entity.Payment;
import com.backend.repository.InvoiceRepository;
import com.backend.repository.PaymentRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;

    @Override
    public PaymentRespDTO makePayment(PaymentDTO dto) {

        if (dto.getInvoiceId() == null) {
            throw new IllegalArgumentException("Invoice ID must not be null");
        }

        // 1️⃣ Load invoice
        Invoice invoice = invoiceRepository.findById(dto.getInvoiceId())
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        // 2️⃣ Check invoice status
        if (invoice.getStatus() == InvoiceStatus.PAID) {
            throw new RuntimeException("Invoice already paid");
        }

       

        // 4️⃣ Create payment
        Payment payment = new Payment();
        payment.setInvoice(invoice);
        payment.setAmount(dto.getAmount());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setPaymentDate(LocalDate.now());

        // 5️⃣ Update invoice status
        invoice.setStatus(InvoiceStatus.PAID);
        invoiceRepository.save(invoice);

        // 6️⃣ Save payment
        Payment saved = paymentRepository.save(payment);

        // 7️⃣ Return response DTO
        return new PaymentRespDTO(
                saved.getId(),
                invoice.getId(),
                invoice.getPatient().getId(),
                invoice.getPatient().getUser().getFirstname() + " " +
                invoice.getPatient().getUser().getLastname(),
                saved.getAmount(),
                saved.getPaymentMethod(),
                saved.getPaymentDate()
        );
    }

    @Override
    public List<PaymentRespDTO> getPaymentsByPatient(Long patientId) {
        return paymentRepository.findByInvoicePatientId(patientId)
                .stream()
                .map(p -> new PaymentRespDTO(
                        p.getId(),
                        p.getInvoice().getId(),
                        p.getInvoice().getPatient().getId(),
                        p.getInvoice().getPatient().getUser().getFirstname() + " " +
                        p.getInvoice().getPatient().getUser().getLastname(),
                        p.getAmount(),
                        p.getPaymentMethod(),
                        p.getPaymentDate()
                ))
                .toList();
    }

    @Override
    public List<PaymentRespDTO> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(p -> new PaymentRespDTO(
                        p.getId(),
                        p.getInvoice().getId(),
                        p.getInvoice().getPatient().getId(),
                        p.getInvoice().getPatient().getUser().getFirstname() + " " +
                        p.getInvoice().getPatient().getUser().getLastname(),
                        p.getAmount(),
                        p.getPaymentMethod(),
                        p.getPaymentDate()
                ))
                .toList();
    }

	@Override
	public byte[] generateReceipt(Long paymentId) {

	    Payment payment = paymentRepository.findById(paymentId)
	            .orElseThrow(() -> new RuntimeException("Payment not found"));

	    try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {

	        Document document = new Document();
	        PdfWriter.getInstance(document, out);
	        document.open();

	        document.add(new Paragraph("HOSPITAL PAYMENT RECEIPT"));
	        document.add(new Paragraph(" "));
	        document.add(new Paragraph("Receipt ID: " + payment.getId()));
	        document.add(new Paragraph("Invoice ID: " + payment.getInvoice().getId()));
	        document.add(new Paragraph("Patient ID: " + payment.getPatient().getId()));
	        document.add(new Paragraph("Amount Paid: ₹" + payment.getAmount()));
	        document.add(new Paragraph("Payment Method: " + payment.getPaymentMethod()));
	        document.add(new Paragraph("Payment Date: " + payment.getPaymentDate()));

	        document.close();
	        return out.toByteArray();

	    } catch (Exception e) {
	        throw new RuntimeException("Failed to generate receipt");
	    }
	}

}



