package com.vmo.FresherManager_PhungNT.repository;

import com.vmo.FresherManager_PhungNT.entity.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {
}
