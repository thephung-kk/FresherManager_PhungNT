package com.vmo.FresherManager_PhungNT.service;

import com.vmo.FresherManager_PhungNT.entity.CenterFresher;
import model.request.CenterFresherCreateRequest;
import model.response.CenterFresherResponse;
import model.response.ResponseObjectRequest;

import java.time.LocalDate;
import java.util.List;

public interface CenterFresherService {
    List<CenterFresherResponse> findAllFreshersByCenterId(Long centerId);
    ResponseObjectRequest addFresherToCenter(CenterFresherCreateRequest centerFresherCreateRequest);
}
