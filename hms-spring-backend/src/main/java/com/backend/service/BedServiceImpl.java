package com.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.dtos.AddBedDTO;
import com.backend.dtos.AssignBedDTO;
import com.backend.dtos.BedDTO;
import com.backend.dtos.BedRespDTO;
import com.backend.dtos.UpdateBedDTO;
import com.backend.entity.Bed;
import com.backend.entity.BedStatus;
import com.backend.entity.Patient;
import com.backend.entity.Ward;
import com.backend.repository.BedRepository;
import com.backend.repository.PatientRepository;
import com.backend.repository.WardRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BedServiceImpl implements BedService {

    private final BedRepository bedRepository;
    private final PatientRepository patientRepository;
    private final WardRepository wardRepository;

    @Override
    @Transactional
    public void assignBedToPatient(AssignBedDTO dto) {

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Bed bed = bedRepository.findById(dto.getBedId())
                .orElseThrow(() -> new RuntimeException("Bed not found"));

        if (bed.getStatus() != BedStatus.AVAILABLE) {
            throw new RuntimeException("Bed not available");
        }

        bed.setPatient(patient);
        bed.setStatus(BedStatus.OCCUPIED);
        patient.setBed(bed);
        bedRepository.save(bed);
    }
    @Override
    public List<BedRespDTO> getBedsByWard(Long wardId) {

        return bedRepository.findByWardId(wardId).stream().map(bed -> {
            BedRespDTO dto = new BedRespDTO();

            dto.setId(bed.getId());
            dto.setBedNumber(bed.getBedNumber());
            dto.setStatus(bed.getStatus().name());
            dto.setWardId(bed.getWard().getId());

            if (bed.getPatient() != null) {
                dto.setPatientId(bed.getPatient().getId());
                dto.setPatientName(
                    //bed.getPatient().getUser().getUsername()
                    bed.getPatient().getUser().getFirstname() + " " +
                    bed.getPatient().getUser().getLastname()
                );
            } else {
                dto.setPatientId(null);
                dto.setPatientName("-");
            }

            return dto;
        }).toList();
    }

    @Override
    public List<BedRespDTO> getAllBeds() {

        return bedRepository.findAll().stream().map(bed -> {
            BedRespDTO dto = new BedRespDTO();
            dto.setId(bed.getId());
            dto.setBedNumber(bed.getBedNumber());
            dto.setStatus(bed.getStatus().name());

            if (bed.getPatient() != null) {
                dto.setPatientId(bed.getPatient().getId());
                dto.setPatientName(
                    bed.getPatient().getUser().getUsername()
                );
            } else {
                dto.setPatientId(null);
                dto.setPatientName("-");
            }


            return dto;
        }).toList();
    }
    @Override
    public void addBed(AddBedDTO dto) {
        Ward ward = wardRepository.findById(dto.getWardId())
            .orElseThrow(() -> new RuntimeException("Ward not found"));

        Bed bed = new Bed();
        bed.setBedNumber(dto.getBedNumber());
        bed.setWard(ward);
        bed.setStatus(BedStatus.AVAILABLE);

        bedRepository.save(bed);
    }
    @Override
    public void releaseBed(Long bedId) {

        Bed bed = bedRepository.findById(bedId)
            .orElseThrow(() -> new RuntimeException("Bed not found"));

        bed.setPatient(null);
        bed.setStatus(BedStatus.CLEANING); // or AVAILABLE

        bedRepository.save(bed);
    }

    @Override
    public void updateBed(Long bedId, UpdateBedDTO dto) {

        Bed bed = bedRepository.findById(bedId)
            .orElseThrow(() -> new RuntimeException("Bed not found"));

        if (dto.getBedNumber() != null) {
            bed.setBedNumber(dto.getBedNumber());
        }

        if (dto.getStatus() != null) {
            bed.setStatus(dto.getStatus());

            // safety: if marking available, remove patient
            if (dto.getStatus() == BedStatus.AVAILABLE) {
                bed.setPatient(null);
            }
        }

        bedRepository.save(bed);
    }

    @Override
    public void emptyBed(Long bedId) {

        Bed bed = bedRepository.findById(bedId)
            .orElseThrow(() -> new RuntimeException("Bed not found"));

        bed.setPatient(null);
        bed.setStatus(BedStatus.AVAILABLE);

        bedRepository.save(bed);
    }

}
