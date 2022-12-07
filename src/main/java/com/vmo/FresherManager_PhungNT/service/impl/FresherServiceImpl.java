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
import java.util.Optional;
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
    public FresherResponse updateFresher(Fresher newFresher, Long fresherId) {
        Fresher updatedFresher = fresherRepository.findById(fresherId).map(fresher -> {
                    fresher.setName(newFresher.getName());
                    fresher.setDob(newFresher.getDob());
                    fresher.setAddress(newFresher.getAddress());
                    fresher.setEmail(newFresher.getEmail());
                    fresher.setPhone(newFresher.getPhone());
                    return fresherRepository.save(fresher);
                })
                .orElseThrow(() -> new EntityNotFoundException(ApiErrorDetail.builder()
                        .message("Fresher not found")
                        .entityName("Fresher")
                        .fieldName("Id")
                        .fieldValue(fresherId)
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .build()));
        FresherResponse f = FresherResponse.builder()
                .fresherId(updatedFresher.getId())
                .fresherName(updatedFresher.getName())
                .address(updatedFresher.getAddress())
                .phone(updatedFresher.getPhone())
                .email(updatedFresher.getEmail())
                .dob(updatedFresher.getDob())
                .build();
        return f;
    }

    @Transactional
    @Override
    public ResponseObjectRequest deleteFresher(Long fresherId) {
        Optional<Fresher> optionalFresher = fresherRepository.findById(fresherId);
        if (optionalFresher.isPresent()) {
            assignmentScoreRepository.deleteByFresherId(fresherId);
            centerFresherRepository.deleteByFresherId(fresherId);
            fresherLanguageRepository.deleteByFresherId(fresherId);
            fresherRepository.deleteById(fresherId);
            return new ResponseObjectRequest("Deleted", "Fresher with id = " + fresherId + " has been deleted!", "");
        } else {
            throw
                    new EntityNotFoundException(ApiErrorDetail.builder()
                            .message("Your search '" + fresherId + "' did not match any fresher.")
                            .entityName("Fresher")
                            .fieldName("Id")
                            .fieldValue(fresherId)
                            .httpStatus(HttpStatus.NOT_FOUND)
                            .build());
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
        FresherResponse f = FresherResponse.builder()
                .fresherId(fresher.getId())
                .fresherName(fresher.getName())
                .address(fresher.getAddress())
                .phone(fresher.getPhone())
                .email(fresher.getEmail())
                .dob(fresher.getDob())
                .build();

        return f;
    }

    @Override
    public List<FresherResponse> findByName(String fresherName) {
        List<FresherResponse> list = fresherRepository.findFresherByNameContaining(fresherName).stream()
                .map(fresher -> FresherResponse.builder()
                        .fresherName(fresher.getName())
                        .fresherId(fresher.getId())
                        .address(fresher.getAddress())
                        .dob(fresher.getDob())
                        .email(fresher.getEmail())
                        .phone(fresher.getPhone()).build())
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            throw
                    new EntityNotFoundException(ApiErrorDetail.builder()
                            .message("Your search '" + fresherName + "' did not match any fresher.")
                            .entityName("Fresher")
                            .fieldName("name")
                            .fieldValue(fresherName)
                            .httpStatus(HttpStatus.NOT_FOUND)
                            .build());
        } else return list;
    }

    @Override
    public List<FresherResponse> findByEmail(String fresherEmail) {
        List<FresherResponse> list = fresherRepository.findAllByEmailContaining(fresherEmail).stream()
                .map(fresher -> FresherResponse.builder()
                        .fresherName(fresher.getName())
                        .fresherId(fresher.getId())
                        .address(fresher.getAddress())
                        .dob(fresher.getDob())
                        .email(fresher.getEmail())
                        .phone(fresher.getPhone()).build())
                .collect(Collectors.toList());

        if (list.isEmpty()) {
            throw
                    new EntityNotFoundException(ApiErrorDetail.builder()
                            .message("Your search '" + fresherEmail + "' did not match any fresher.")
                            .entityName("Fresher")
                            .fieldName("email")
                            .fieldValue(fresherEmail)
                            .httpStatus(HttpStatus.NOT_FOUND)
                            .build());
        } else return list;
    }

    @Override
    public ResponseObjectRequest countAllFresher() {
        return new ResponseObjectRequest("Done", "Number of fresher is: " + fresherRepository.findAll().size(), "");
    }


}
