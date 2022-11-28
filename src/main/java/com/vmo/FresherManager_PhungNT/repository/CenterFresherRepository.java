package com.vmo.FresherManager_PhungNT.repository;

import com.vmo.FresherManager_PhungNT.entity.CenterFresher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CenterFresherRepository extends JpaRepository<CenterFresher,Long>{
    long deleteByFresherId(Long id);

    List<CenterFresher> findAllByCenterId(Long centerId);

    long deleteByCenterId(Long id);
}
