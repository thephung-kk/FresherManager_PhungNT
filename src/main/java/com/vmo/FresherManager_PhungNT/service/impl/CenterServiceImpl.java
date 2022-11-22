package com.vmo.FresherManager_PhungNT.service.impl;

import com.vmo.FresherManager_PhungNT.entity.Center;
import com.vmo.FresherManager_PhungNT.repository.CenterFresherRepository;
import com.vmo.FresherManager_PhungNT.repository.CenterRepository;
import com.vmo.FresherManager_PhungNT.service.CenterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.request.CenterCreateRequest;
import model.response.ResponseObjectRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {

    private final CenterRepository centerRepository;
    private final CenterFresherRepository centerFresherRepository;

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
        return centerRepository.findAll()
                .stream().map(Center::getName).collect(Collectors.toList());
    }

    @Override
    public ResponseObjectRequest updateCenter(Center newCenter, Long id) {
        boolean exist = centerRepository.existsById(id);
        if (exist) {
            Center updateCenter = centerRepository.findById(id)
                    .map(center -> {
                        center.setAddress(newCenter.getAddress());
                        center.setDob(newCenter.getDob());
                        center.setName(newCenter.getName());
                        center.setCode(newCenter.getCode());
                        center.setCenterFresherList(newCenter.getCenterFresherList());
                        return centerRepository.save(center);
                    }).orElseGet(() -> {
                        newCenter.setId(id);
                        return centerRepository.save(newCenter);
                    });
            return new ResponseObjectRequest("Updated", "Center with id = " + id + " has been updated", updateCenter);
        } else {
            return new ResponseObjectRequest("Failed", "Cannot find center with id = " + id, "");
        }
    }

    @Transactional
    @Override
    public ResponseObjectRequest deleteCenter(Long id) {
        boolean exist = centerRepository.existsById(id);
        if (exist){
            centerFresherRepository.deleteByCenterId(id);
            centerRepository.deleteById(id);
            return new ResponseObjectRequest(
                    "Deleted","Center with id = " + id + " has been deleted",""
            );
        } else {
            return new ResponseObjectRequest(
                    "Failed","Cannot find center with id = "+ id,""
            );
        }
    }
}
