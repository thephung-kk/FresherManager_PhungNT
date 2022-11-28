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

import javax.validation.constraints.Null;
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
            throw
                    new EntityNotFoundException(ApiErrorDetail.builder()
                            .message("Your search '"+ id +"' did not match any fresher.")
                            .entityName("Fresher")
                            .fieldName("id")
                            .fieldValue(id)
                            .httpStatus(HttpStatus.NOT_FOUND)
                            .build());
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
            throw
                    new EntityNotFoundException(ApiErrorDetail.builder()
                            .message("Your search '"+ id +"' did not match any fresher.")
                            .entityName("Fresher")
                            .fieldName("Id")
                            .fieldValue(id)
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

    @Transactional
    @Override
    public ResponseObjectRequest findById(Long fresherId) {
        boolean exist = fresherRepository.existsById(fresherId);
        if(exist) {
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
            return new ResponseObjectRequest("Found", "Fresher has ID = " + fresherId + ": ", f);
        } else {
            throw
                    new EntityNotFoundException(ApiErrorDetail.builder()
                            .message("Your search '"+ fresherId +"' did not match any fresher.")
                            .entityName("Fresher")
                            .fieldName("id")
                            .fieldValue(fresherId)
                            .httpStatus(HttpStatus.NOT_FOUND)
                            .build());
        }
    }

    @Override
    public ResponseObjectRequest findByName(String fresherName) {
        List<FresherResponse> list =  fresherRepository.findFresherByNameContaining(fresherName).stream()
                .map(fresher -> FresherResponse.builder()
                        .fresherName(fresher.getName())
                        .fresherId(fresher.getId())
                        .address(fresher.getAddress())
                        .dob(fresher.getDob())
                        .email(fresher.getEmail())
                        .phone(fresher.getPhone()).build())
                .collect(Collectors.toList());
        if(list.isEmpty()){
            throw
                    new EntityNotFoundException(ApiErrorDetail.builder()
                            .message("Your search '"+ fresherName +"' did not match any fresher.")
                            .entityName("Fresher")
                            .fieldName("name")
                            .fieldValue(fresherName)
                            .httpStatus(HttpStatus.NOT_FOUND)
                            .build());
        }
        else return
                new ResponseObjectRequest("Found","Found "+list.size()+" freshers contain \'" + fresherName+"\'"
                ,list);
    }

    @Override
    public ResponseObjectRequest findByEmail(String fresherEmail) {
        List<FresherResponse> list =  fresherRepository.findAllByEmail(fresherEmail).stream()
                .map(fresher -> FresherResponse.builder()
                        .fresherId(fresher.getId())
                        .address(fresher.getAddress())
                        .dob(fresher.getDob())
                        .email(fresher.getEmail())
                        .phone(fresher.getPhone()).build())
                .collect(Collectors.toList());
        if(list.isEmpty()){
            throw
                    new EntityNotFoundException(ApiErrorDetail.builder()
                            .message("Your search '"+ fresherEmail +"' did not match any fresher.")
                            .entityName("Fresher")
                            .fieldName("Email")
                            .fieldValue(fresherEmail)
                            .httpStatus(HttpStatus.NOT_FOUND)
                            .build());
        }
        else return
                new ResponseObjectRequest("Found","Found "+list.size()+" freshers has email: \'" + fresherEmail+"\'"
                        ,list);
    }


}
