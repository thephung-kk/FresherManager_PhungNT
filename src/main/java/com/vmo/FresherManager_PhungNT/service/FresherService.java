package com.vmo.FresherManager_PhungNT.service;

import com.vmo.FresherManager_PhungNT.entity.Fresher;
import model.request.FresherCreateRequest;
import model.response.FresherResponse;
import model.response.ResponseObjectRequest;

import java.util.List;

public interface FresherService {

    Fresher createFresher(FresherCreateRequest fresherCreateRequest);
    List<String> findAllFresher();
    FresherResponse findById(Long fresherId); //
    List<FresherResponse> findByName(String fresherName); //

    ResponseObjectRequest deleteFresher(Long id);

    ResponseObjectRequest countAllFresher();
    List<FresherResponse> findByEmail(String fresherEmail); //
    FresherResponse updateFresher(Fresher newFresher, Long id); //

}
