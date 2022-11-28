package com.vmo.FresherManager_PhungNT.service;


import model.response.AssignmentScoreResponse;
import model.response.ResponseObjectRequest;

import java.util.List;

public interface AssignmentScoreService {
    List<AssignmentScoreResponse.ScoreResponse> findAllAssignmentByFresherId(Long id);
    ResponseObjectRequest finalScore(Long fresherId);
    Double avgScore(Long fresherID);
    ResponseObjectRequest findAllFresherByAvgScore(Long score);
}
