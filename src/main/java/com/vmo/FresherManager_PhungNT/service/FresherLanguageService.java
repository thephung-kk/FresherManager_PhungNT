package com.vmo.FresherManager_PhungNT.service;

import com.vmo.FresherManager_PhungNT.entity.FresherLanguage;
import model.request.FresherLanguageCreateRequest;

import java.util.List;

public interface FresherLanguageService {
    FresherLanguage createFresherLanguage(FresherLanguageCreateRequest fresherLanguageCreateRequest);
    List<String> findAllFresherLanguage();
}