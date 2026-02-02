package com.backend.dtos;

import com.backend.entity.AppointmentStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentStatusUpdateDto {
    private AppointmentStatus status; 
}

