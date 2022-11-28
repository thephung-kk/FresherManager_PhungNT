package com.vmo.FresherManager_PhungNT.service.impl;

import com.vmo.FresherManager_PhungNT.entity.CenterFresher;
import com.vmo.FresherManager_PhungNT.repository.CenterFresherRepository;
import com.vmo.FresherManager_PhungNT.service.CenterFresherService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.request.CenterFresherCreateRequest;
import model.response.CenterFresherResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CenterFresherServiceImpl implements CenterFresherService {

    private final CenterFresherRepository centerFresherRepository;


    @Override
    public CenterFresher createCenterFresher(CenterFresherCreateRequest centerFresherCreateRequest) {
        return null;
    }

    @Override
    public List<CenterFresherResponse> findAllFreshersByCenterId(Long centerId) {
        return centerFresherRepository.findAllByCenterId(centerId).stream()
                .map(centerFresher -> CenterFresherResponse.builder()
                        .centerId(centerFresher.getCenter().getId())
                        .centerName(centerFresher.getCenter().getName())
                        .fresherId(centerFresher.getFresher().getId())
                        .fresherName(centerFresher.getFresher().getName()).build())
                .collect(Collectors.toList());
    }
}

