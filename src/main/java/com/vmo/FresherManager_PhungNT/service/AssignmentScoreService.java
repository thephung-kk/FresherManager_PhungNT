package com.vmo.FresherManager_PhungNT.service;

import com.vmo.FresherManager_PhungNT.entity.AssignmentScore;
import model.request.AssignmentScoreCreateRequest;

import java.util.List;

public interface AssignmentScoreService {
    AssignmentScore createAssignmentScore(AssignmentScoreCreateRequest assignmentScoreCreateRequest);
    List<String> findAllAssignmentScore();
}
