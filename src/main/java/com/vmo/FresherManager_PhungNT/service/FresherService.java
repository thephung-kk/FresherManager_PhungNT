package com.vmo.FresherManager_PhungNT.service;

import com.vmo.FresherManager_PhungNT.entity.Fresher;
import model.request.FresherCreateRequest;
import model.response.ResponseObjectRequest;

import java.util.List;

public interface FresherService {

    Fresher createFresher(FresherCreateRequest fresherCreateRequest);
    ResponseObjectRequest updateFresher(Fresher newFresher, Long id);
    ResponseObjectRequest deleteFresher(Long id);

    List<String> findAllFresher();
}