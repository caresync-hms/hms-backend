package com.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.dtos.WardDTO;
import com.backend.repository.WardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WardServiceImpl implements WardService {

    private final WardRepository wardRepository;

    @Override
    public List<WardDTO> getAllWards() {
        return wardRepository.findAll().stream().map(ward -> {
            WardDTO dto = new WardDTO();
            dto.setId(ward.getId());
            dto.setWardName(ward.getWardName());
            dto.setDepartmentId(ward.getDepartment().getId());
            dto.setStatus(ward.getStatus());
            return dto;
        }).toList();
    }
}
