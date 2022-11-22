package com.vmo.FresherManager_PhungNT.service.impl;

import com.vmo.FresherManager_PhungNT.entity.FresherLanguage;
import com.vmo.FresherManager_PhungNT.repository.FresherLanguageRepository;
import com.vmo.FresherManager_PhungNT.service.FresherLanguageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.request.FresherLanguageCreateRequest;
import model.response.FresherLanguageResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<FresherLanguageResponse> findAllFresherByLanguage(Long languageId) {
        return fresherLanguageRepository.findAllByLanguage_Id(languageId).stream()
                .map(fresherLanguage -> FresherLanguageResponse.builder()
                        .languageId(fresherLanguage.getLanguage().getId())
                        .languageName(fresherLanguage.getLanguage().getName())
                        .fresherId(fresherLanguage.getFresher().getId())
                        .fresherName(fresherLanguage.getFresher().getName())
                        .build())
                .collect(Collectors.toList());

    }

}
