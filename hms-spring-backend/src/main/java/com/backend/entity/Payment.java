package com.backend.entity;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
	import java.time.LocalDate;

	import jakarta.persistence.*;
	import lombok.*;

	@Entity
	@Table(name = "payment")
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@AttributeOverride(
	    name = "id",
	    column = @Column(name = "payment_id")
	)
	public class Payment extends Base {

	    @ManyToOne
	    @JoinColumn(name = "patient_id", nullable = false)
	    private Patient patient;   

	    @ManyToOne
	    @JoinColumn(name = "invoice_id", nullable = false)
	    private Invoice invoice;

	    @Column(nullable = false)
	    private BigDecimal amount;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private PaymentMethod paymentMethod;

	    @Column(nullable = false)
	    private LocalDate paymentDate;

	    /*@ManyToOne
	    @JoinColumn(name = "receptionist_id", nullable = false)
	    private Receptionist receptionist;*/
	}



