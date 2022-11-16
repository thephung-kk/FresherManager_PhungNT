package com.vmo.FresherManager_PhungNT.service;

import com.vmo.FresherManager_PhungNT.entity.Center;
import model.request.CenterCreateRequest;

import java.util.List;

public interface CenterService {
    Center createCenter(CenterCreateRequest centerCreateRequest);
    List<String> findAllCenter();
}
