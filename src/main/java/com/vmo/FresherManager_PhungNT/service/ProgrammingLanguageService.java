package com.vmo.FresherManager_PhungNT.service;

import com.vmo.FresherManager_PhungNT.entity.ProgrammingLanguage;
import model.request.ProgrammingLanguageCreateRequest;

import java.util.List;

public interface ProgrammingLanguageService {
    ProgrammingLanguage createProgrammingLanguage(ProgrammingLanguageCreateRequest programmingLanguageCreateRequest);
    List<String> findAllLanguage();
}
