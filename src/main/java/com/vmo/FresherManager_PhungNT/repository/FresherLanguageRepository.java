package com.vmo.FresherManager_PhungNT.repository;

import com.vmo.FresherManager_PhungNT.entity.FresherLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FresherLanguageRepository extends JpaRepository<FresherLanguage,Long>{
    long deleteByFresherId(Long id);
    List<FresherLanguage> findAllByLanguage_Id(Long languageId);
    List<FresherLanguage> findAllByLanguage_Name(String languageName);
}
