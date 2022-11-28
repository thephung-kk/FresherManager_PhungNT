package com.vmo.FresherManager_PhungNT.service.impl;

import com.vmo.FresherManager_PhungNT.repository.AssignmentScoreRepository;
import com.vmo.FresherManager_PhungNT.service.AssignmentScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.response.AssignmentScoreResponse;
import model.response.ResponseObjectRequest;
import org.springframework.stereotype.Service;
import springfox.documentation.service.Response;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssignmentScoreServiceImpl implements AssignmentScoreService {

    private final AssignmentScoreRepository assignmentScoreRepository;


    @Override
    public List<AssignmentScoreResponse.ScoreResponse> findAllAssignmentByFresherId(Long id) {
        return assignmentScoreRepository.findAllByFresherId(id).stream()
                .map(assignmentScore -> AssignmentScoreResponse.ScoreResponse.builder()
                        .assignmentId(assignmentScore.getAssignment().getId())
                        .fresherId(assignmentScore.getFresher().getId())
                        .fresherName(assignmentScore.getFresher().getName())
                        .score(assignmentScore.getScore())
                        .percentage(assignmentScore.getAssignment().getPercentage()).build())
                .collect(Collectors.toList());
    }

    @Override
    public ResponseObjectRequest finalScore(Long fresherId) {
        List<AssignmentScoreResponse.ScoreResponse> list = assignmentScoreRepository.findAllByFresherId(fresherId).stream()
                .map(assignmentScore -> AssignmentScoreResponse.ScoreResponse.builder()
                        .assignmentId(assignmentScore.getAssignment().getId())
                        .fresherId(assignmentScore.getFresher().getId())
                        .fresherName(assignmentScore.getFresher().getName())
                        .score(assignmentScore.getScore())
                        .percentage(assignmentScore.getAssignment().getPercentage())
                        .build())
                .collect(Collectors.toList());
        Double finalScore = 0.0;
        for (AssignmentScoreResponse.ScoreResponse assScore : list
        ) {
            finalScore += assScore.getScore().doubleValue() * assScore.getPercentage().doubleValue() / 100;
        }
        return new ResponseObjectRequest("ID Fresher found","Final score: " + finalScore,list);
    }
}
