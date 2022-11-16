package com.vmo.FresherManager_PhungNT.repository;

import com.vmo.FresherManager_PhungNT.entity.FresherLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FresherLanguageRepository extends JpaRepository<FresherLanguage,Long>{
    long deleteByFresherId(Long id);
}
