package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Bed;
import com.backend.entity.BedStatus;

public interface BedRepository extends JpaRepository<Bed, Long> {
    List<Bed> findByWardIdAndStatus(Long wardId,BedStatus status );
    List<Bed> findByWardId(Long wardId);
}

