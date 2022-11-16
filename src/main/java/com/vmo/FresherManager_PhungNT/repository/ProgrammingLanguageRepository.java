package com.vmo.FresherManager_PhungNT.repository;

import com.vmo.FresherManager_PhungNT.entity.ProgrammingLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammingLanguageRepository extends JpaRepository<ProgrammingLanguage,Long> {
}
