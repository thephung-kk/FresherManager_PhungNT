package com.vmo.FresherManager_PhungNT.service;

import com.vmo.FresherManager_PhungNT.entity.Center;
import model.request.CenterCreateRequest;
import model.response.CenterResponse;
import model.response.ResponseObjectRequest;

import java.util.List;

public interface CenterService {
    Center createCenter(CenterCreateRequest centerCreateRequest);
    List<String> findAllCenter();
    CenterResponse updateCenter(Center newCenter, Long id);
    ResponseObjectRequest deleteCenter(Long id);
}
