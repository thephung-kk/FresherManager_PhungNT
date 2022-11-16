package com.vmo.FresherManager_PhungNT.repository;

import com.vmo.FresherManager_PhungNT.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}
