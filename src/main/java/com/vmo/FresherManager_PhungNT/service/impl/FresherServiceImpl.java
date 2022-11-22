package com.vmo.FresherManager_PhungNT.service.impl;

import com.vmo.FresherManager_PhungNT.entity.Fresher;
import com.vmo.FresherManager_PhungNT.exception.ApiErrorDetail;
import com.vmo.FresherManager_PhungNT.exception.EntityNotFoundException;
import com.vmo.FresherManager_PhungNT.repository.*;
import com.vmo.FresherManager_PhungNT.service.FresherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.request.FresherCreateRequest;
import model.response.FresherResponse;
import model.response.ResponseObjectRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j //?
@RequiredArgsConstructor
public class FresherServiceImpl implements FresherService {

    private final FresherRepository fresherRepository;
    private final AssignmentScoreRepository assignmentScoreRepository;
    private final FresherLanguageRepository fresherLanguageRepository;
    private final CenterFresherRepository centerFresherRepository;

    @Override
    @Transactional
    public Fresher createFresher(FresherCreateRequest fresherCreateRequest) {
        var fresher = Fresher.builder()
                .name(fresherCreateRequest.getName())
                .phone(fresherCreateRequest.getPhone())
                .email(fresherCreateRequest.getEmail())
                .dob(fresherCreateRequest.getDob())
                .address(fresherCreateRequest.getAddress())
                .build();
        return fresherRepository.save(fresher);
    }

    @Override
    public ResponseObjectRequest updateFresher(Fresher newFresher, Long id) {
        boolean exist = fresherRepository.existsById(id);
        if (exist) {
            Fresher updateFresher = fresherRepository.findById(id)
                    .map(fresher -> {
                        fresher.setName(newFresher.getName());
                        fresher.setDob(newFresher.getDob());
                        fresher.setAddress(newFresher.getAddress());
                        fresher.setEmail(newFresher.getEmail());
                        fresher.setPhone(newFresher.getPhone());
                        fresher.setCenterFresherList(newFresher.getCenterFresherList());
                        fresher.setFresherLanguageList(newFresher.getFresherLanguageList());
                        fresher.setAssignmentScoreList(newFresher.getAssignmentScoreList());
                        return fresherRepository.save(fresher);
                    }).orElseGet(() -> {
                        newFresher.setId(id);
                        return fresherRepository.save(newFresher);
                    });
            return new ResponseObjectRequest("Updated", "Fresher with id = " + id + " has been updated", updateFresher);
        } else {
            return new ResponseObjectRequest("Failed", "Cannot find fresher with id = " + id, "");
        }

    }

    @Transactional
    @Override
    public ResponseObjectRequest deleteFresher(Long id) {
        boolean exist = fresherRepository.existsById(id);
        if (exist) {
            assignmentScoreRepository.deleteByFresherId(id);
            centerFresherRepository.deleteByFresherId(id);
            fresherLanguageRepository.deleteByFresherId(id);
            fresherRepository.deleteById(id);
            return new ResponseObjectRequest(
                    "deleted", "Fresher with id = " + id + " has been deleted", "");
        } else {
            return new ResponseObjectRequest(
                    "failed", "Cannot find fresher with id = " + id, "");
        }
    }

    @Override
    public List<String> findAllFresher() {
        return fresherRepository.findAll()
                .stream()
                .map(Fresher::getName)
                .collect(Collectors.toList());
    }

    @Override
    public FresherResponse findById(Long fresherId) {
        Fresher fresher = fresherRepository.findById(fresherId)
                .orElseThrow(() -> new EntityNotFoundException(ApiErrorDetail.builder()
                        .message("Fresher not found")
                        .entityName("Fresher")
                        .fieldName("Id")
                        .fieldValue(fresherId)
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .build()));
        return FresherResponse.builder()
                .fresherId(fresher.getId())
                .fresherName(fresher.getName())
                .address(fresher.getAddress())
                .phone(fresher.getPhone())
                .email(fresher.getEmail())
                .dob(fresher.getDob())
                .build();
    }

    @Override
    public List<FresherResponse> findByName(String fresherName) {
        return fresherRepository.findAllByName(fresherName).stream()
                .map(fresher -> FresherResponse.builder()
                        .fresherId(fresher.getId())
                        .address(fresher.getAddress())
                        .dob(fresher.getDob())
                        .email(fresher.getEmail())
                        .phone(fresher.getPhone()).build())
                .collect(Collectors.toList());

    }

    @Override
    public List<FresherResponse> findByEmail(String fresherEmail) {
        return fresherRepository.findAllByEmail(fresherEmail).stream()
                .map(fresher -> FresherResponse.builder()
                        .fresherId(fresher.getId())
                        .address(fresher.getAddress())
                        .dob(fresher.getDob())
                        .email(fresher.getEmail())
                        .phone(fresher.getPhone()).build())
                .collect(Collectors.toList());
    }


}
