package com.vmo.FresherManager_PhungNT.repository;

import com.vmo.FresherManager_PhungNT.entity.Fresher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FresherRepository extends JpaRepository<Fresher,Long> {
    List<Fresher> findFresherByNameContaining(String fresherName);
    List<Fresher> findAllByEmailContaining(String fresherEmail);


}
