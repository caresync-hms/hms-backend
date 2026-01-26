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
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


	
	@Entity
	@Table(name = "bed")
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor

	public class Bed {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer bedId;

	    @ManyToOne
	    @JoinColumn(name = "ward_id")
	    private Ward ward;

	    @Enumerated(EnumType.STRING)
	    private BedStatus status;
	}


