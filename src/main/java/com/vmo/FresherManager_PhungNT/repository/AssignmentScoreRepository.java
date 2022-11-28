package com.vmo.FresherManager_PhungNT.repository;

import com.vmo.FresherManager_PhungNT.entity.AssignmentScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentScoreRepository extends JpaRepository<AssignmentScore,Long>{
    long deleteByFresherId(Long id);
    List<AssignmentScore> findAllByFresherId(Long fresherId);
}
