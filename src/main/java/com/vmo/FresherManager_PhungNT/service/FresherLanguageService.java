package com.vmo.FresherManager_PhungNT.service;

import com.vmo.FresherManager_PhungNT.entity.FresherLanguage;
import model.request.FresherLanguageCreateRequest;
import model.response.FresherLanguageResponse;
import model.response.ResponseObjectRequest;

import java.util.List;

public interface FresherLanguageService {
    FresherLanguage createFresherLanguage(FresherLanguageCreateRequest fresherLanguageCreateRequest);
    ResponseObjectRequest findAllFresherByLanguageID(Long languageId);
    ResponseObjectRequest findAllFresherByLanguage(String languageName);
}
