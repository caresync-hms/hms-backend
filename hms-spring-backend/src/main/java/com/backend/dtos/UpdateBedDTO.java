package com.backend.dtos;

import com.backend.entity.BedStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBedDTO {
    private String bedNumber;
    private BedStatus status;
}

