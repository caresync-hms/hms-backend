package com.backend.entity;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


	@Entity
	@Table(name = "ward")
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public class Ward {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer wardId;

	    private String wardType;
	    private Integer capacity;

	  /*  @ManyToOne
	    @JoinColumn(name = "receptionist_id")
	    private Receptionist receptionist;*/
	}


