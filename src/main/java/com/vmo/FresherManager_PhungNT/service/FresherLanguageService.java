package com.vmo.FresherManager_PhungNT.service;

import model.response.FresherLanguageResponse;
import model.response.ResponseObjectRequest;

import java.util.List;

public interface FresherLanguageService {
    List<FresherLanguageResponse> findAllFresherByLanguageID(Long languageId);
    List<FresherLanguageResponse> findAllFresherByLanguage(String languageName);
}
