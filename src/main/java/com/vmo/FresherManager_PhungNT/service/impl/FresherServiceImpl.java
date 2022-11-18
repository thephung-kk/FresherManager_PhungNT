package com.vmo.FresherManager_PhungNT.service.impl;

import com.vmo.FresherManager_PhungNT.entity.Fresher;
import com.vmo.FresherManager_PhungNT.repository.*;
import com.vmo.FresherManager_PhungNT.service.FresherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.request.FresherCreateRequest;
import model.response.ResponseObjectRequest;
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
}
