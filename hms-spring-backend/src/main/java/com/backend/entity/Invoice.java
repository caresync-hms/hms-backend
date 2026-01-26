package com.backend.entity;

    import java.time.LocalDate;
    import java.time.LocalDateTime;

	import java.math.BigDecimal;
	import java.time.LocalDate;

	import jakarta.persistence.*;
	import lombok.*;

	@Entity
	@Table(name = "invoice")
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@AttributeOverride(
	    name = "id",
	    column = @Column(name = "invoice_id")
	)
	public class Invoice extends Base {

	    @ManyToOne
	    @JoinColumn(name = "patient_id", nullable = false)
	    private Patient patient;

	    @Column(nullable = false)
	    private BigDecimal totalAmount;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private InvoiceStatus status;

	    @Column(nullable = false)
	    private LocalDate createdDate;
	}



