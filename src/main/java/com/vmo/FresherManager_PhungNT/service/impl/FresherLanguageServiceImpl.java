package com.vmo.FresherManager_PhungNT.service.impl;

import com.vmo.FresherManager_PhungNT.entity.FresherLanguage;
import com.vmo.FresherManager_PhungNT.repository.FresherLanguageRepository;
import com.vmo.FresherManager_PhungNT.service.FresherLanguageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.request.FresherLanguageCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FresherLanguageServiceImpl implements FresherLanguageService {

    private final FresherLanguageRepository fresherLanguageRepository;

    @Transactional
    @Override
    public FresherLanguage createFresherLanguage(FresherLanguageCreateRequest fresherLanguageCreateRequest) {
        return null;
    }

    @Override
    public List<String> findAllFresherLanguage() {
        return null;
    }
}
