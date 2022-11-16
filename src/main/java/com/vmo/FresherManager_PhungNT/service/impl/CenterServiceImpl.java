package com.vmo.FresherManager_PhungNT.service.impl;

import com.vmo.FresherManager_PhungNT.entity.Center;
import com.vmo.FresherManager_PhungNT.repository.CenterRepository;
import com.vmo.FresherManager_PhungNT.service.CenterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.request.CenterCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {

    private final CenterRepository centerRepository;

    @Transactional
    @Override
    public Center createCenter(CenterCreateRequest centerCreateRequest) {
        var center= Center.builder()
                .name(centerCreateRequest.getName())
                .code(centerCreateRequest.getCode())
                .address(centerCreateRequest.getAddress())
                .dob(centerCreateRequest.getDob())
                .build();
        return centerRepository.save(center);
    }

    @Override
    public List<String> findAllCenter() {
        return centerRepository.findAll().stream().map(Center::getName).collect(Collectors.toList());
    }
}
