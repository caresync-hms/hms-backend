package com.backend.dtos;

import com.backend.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WardDTO {
    private Long id;
    private String wardName;
    private Long departmentId;
    private Status status;
}

