package com.vmo.FresherManager_PhungNT.service;

import com.vmo.FresherManager_PhungNT.entity.CenterFresher;
import model.request.CenterFresherCreateRequest;
import model.response.CenterFresherResponse;

import java.util.List;

public interface CenterFresherService {
    CenterFresher createCenterFresher(CenterFresherCreateRequest centerFresherCreateRequest);
    List<CenterFresherResponse> findAllFreshersById(Long centerId);
}
