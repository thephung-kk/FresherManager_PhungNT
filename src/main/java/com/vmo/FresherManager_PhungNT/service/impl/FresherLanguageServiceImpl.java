package com.vmo.FresherManager_PhungNT.service.impl;

import com.vmo.FresherManager_PhungNT.exception.ApiErrorDetail;
import com.vmo.FresherManager_PhungNT.exception.EntityNotFoundException;
import com.vmo.FresherManager_PhungNT.repository.FresherLanguageRepository;
import com.vmo.FresherManager_PhungNT.service.FresherLanguageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.response.FresherLanguageResponse;
import model.response.ResponseObjectRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FresherLanguageServiceImpl implements FresherLanguageService {

    private final FresherLanguageRepository fresherLanguageRepository;

    @Override
    public List<FresherLanguageResponse> findAllFresherByLanguageID(Long languageId) {
        List<FresherLanguageResponse> list =  fresherLanguageRepository.findAllByLanguage_Id(languageId).stream()
                .map(fresherLanguage -> FresherLanguageResponse.builder()
                        .languageId(fresherLanguage.getLanguage().getId())
                        .languageName(fresherLanguage.getLanguage().getName())
                        .fresherId(fresherLanguage.getFresher().getId())
                        .fresherName(fresherLanguage.getFresher().getName())
                        .build())
                .collect(Collectors.toList());
        if(list.isEmpty()){
            throw new
                    EntityNotFoundException(ApiErrorDetail.builder()
                    .message("Your search '"+ languageId +"' did not match any fresher.")
                    .entityName("Fresher")
                    .fieldName("language ID")
                    .fieldValue(languageId)
                    .httpStatus(HttpStatus.NOT_FOUND).build());
        }
        else {
            return list;
        }
    }

    @Override
    public List<FresherLanguageResponse> findAllFresherByLanguage(String languageName) {
        List<FresherLanguageResponse> list = fresherLanguageRepository.findAllByLanguage_Name(languageName).stream()
                .map(fresherLanguage -> FresherLanguageResponse.builder()
                        .languageId(fresherLanguage.getLanguage().getId())
                        .languageName(fresherLanguage.getLanguage().getName())
                        .fresherId(fresherLanguage.getFresher().getId())
                        .fresherName(fresherLanguage.getFresher().getName())
                        .build())
                .collect(Collectors.toList());
        if(list.isEmpty()){
            throw new
                    EntityNotFoundException(ApiErrorDetail.builder()
                    .message("Your search '"+ languageName +"' did not match any fresher.")
                    .entityName("Fresher")
                    .fieldName("languageName")
                    .fieldValue(languageName)
                    .httpStatus(HttpStatus.NOT_FOUND).build());
        }
        else {
            return list;
        }
    }

}
