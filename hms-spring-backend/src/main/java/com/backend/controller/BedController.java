package com.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dtos.AddBedDTO;
import com.backend.dtos.AssignBedDTO;
import com.backend.dtos.BedRespDTO;
import com.backend.dtos.UpdateBedDTO;
import com.backend.service.BedService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/receptionist/beds")
@RequiredArgsConstructor
public class BedController {

    private final BedService bedService;
    

    @GetMapping
    public List<BedRespDTO> getAllBeds() {
        return bedService.getAllBeds();
    }

    @PostMapping("/assign")
    public void assignBed(@RequestBody AssignBedDTO dto) {
        bedService.assignBedToPatient(dto);
    }
    // ✅ ADD BED
    @PostMapping
    public void addBed(@RequestBody AddBedDTO dto) {
        bedService.addBed(dto);
    }
    @PostMapping("/{bedId}/release")
    public void releaseBed(@PathVariable Long bedId) {
        bedService.releaseBed(bedId);
    }
    @PutMapping("/{bedId}")
    public void updateBed(
        @PathVariable Long bedId,
        @RequestBody UpdateBedDTO dto
    ) {
        bedService.updateBed(bedId, dto);
    }

    @PostMapping("/{bedId}/empty")
    public void emptyBed(@PathVariable Long bedId) {
        bedService.emptyBed(bedId);
    }
}
