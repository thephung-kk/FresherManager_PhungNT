package com.vmo.FresherManager_PhungNT.service.impl;

import com.vmo.FresherManager_PhungNT.entity.ProgrammingLanguage;
import com.vmo.FresherManager_PhungNT.repository.ProgrammingLanguageRepository;
import com.vmo.FresherManager_PhungNT.service.ProgrammingLanguageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.request.ProgrammingLanguageCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProgrammingLanguageServiceImpl implements ProgrammingLanguageService {

    private final ProgrammingLanguageRepository programmingLanguageRepository;

    @Transactional
    @Override
    public ProgrammingLanguage createProgrammingLanguage(ProgrammingLanguageCreateRequest programmingLanguageCreateRequest) {
        return null;
    }

    @Override
    public List<String> findAllLanguage() {
        return null;
    }
}
