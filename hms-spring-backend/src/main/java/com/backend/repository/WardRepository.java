package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Bed;
import com.backend.entity.Ward;

public interface WardRepository extends JpaRepository<Ward, Long> {
    List<Ward> findByDepartmentId(Long departmentId);
   // List<Bed> findByWardId(Long wardId);
}
