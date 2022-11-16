package com.vmo.FresherManager_PhungNT.service.impl;

import com.vmo.FresherManager_PhungNT.entity.AssignmentScore;
import com.vmo.FresherManager_PhungNT.repository.AssignmentScoreRepository;
import com.vmo.FresherManager_PhungNT.service.AssignmentScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.request.AssignmentScoreCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssignmentScoreServiceImpl implements AssignmentScoreService {

    private final AssignmentScoreRepository assignmentScoreRepository;

    @Transactional
    @Override
    public AssignmentScore createAssignmentScore(AssignmentScoreCreateRequest assignmentScoreCreateRequest) {
        return null;
    }

    @Override
    public List<String> findAllAssignmentScore() {
        return null;
    }
}
