package com.backend.service;

import java.util.List;

import com.backend.dtos.AddBedDTO;
import com.backend.dtos.AssignBedDTO;
import com.backend.dtos.BedDTO;
import com.backend.dtos.BedRespDTO;
import com.backend.dtos.UpdateBedDTO;

public interface BedService {
	List<BedRespDTO> getBedsByWard(Long wardId);
    void assignBedToPatient(AssignBedDTO dto);
    List<BedRespDTO> getAllBeds();
    void addBed(AddBedDTO dto);
	void releaseBed(Long bedId);
	void updateBed(Long bedId, UpdateBedDTO dto);
	void emptyBed(Long bedId);

}
