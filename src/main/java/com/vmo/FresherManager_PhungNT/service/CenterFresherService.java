package com.vmo.FresherManager_PhungNT.service;

import com.vmo.FresherManager_PhungNT.entity.CenterFresher;
import model.request.CenterFresherCreateRequest;

import java.util.List;

public interface CenterFresherService {
    CenterFresher createCenterFresher(CenterFresherCreateRequest centerFresherCreateRequest);
    List<String> findAllCenterFresher();
}
