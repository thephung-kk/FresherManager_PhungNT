package com.vmo.FresherManager_PhungNT.service.impl;

import com.vmo.FresherManager_PhungNT.entity.CenterFresher;
import com.vmo.FresherManager_PhungNT.repository.CenterFresherRepository;
import com.vmo.FresherManager_PhungNT.service.CenterFresherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.request.CenterFresherCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CenterFresherServiceImpl implements CenterFresherService {

    private final CenterFresherRepository centerFresherRepository;

    @Transactional
    @Override
    public CenterFresher createCenterFresher(CenterFresherCreateRequest centerFresherCreateRequest) {
        return null;
    }

    @Override
    public List<String> findAllCenterFresher() {
        return null;
    }
}
