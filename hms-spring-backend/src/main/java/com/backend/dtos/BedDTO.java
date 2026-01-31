package com.backend.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BedDTO {
    private Long id;
    private String bedNumber;
    private Long wardId;
    private boolean occupied;
    private Long patientId;
}

