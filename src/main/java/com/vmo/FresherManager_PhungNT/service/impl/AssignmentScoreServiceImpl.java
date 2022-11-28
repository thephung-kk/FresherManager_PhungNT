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
        List<AssignmentScoreResponse.ScoreResponse> list = findAllAssignmentByFresherId(fresherId);
        Double finalScore = 0.0;
        for (AssignmentScoreResponse.ScoreResponse assScore : list
        ) {
            finalScore += assScore.getScore().doubleValue() * assScore.getPercentage().doubleValue() / 100;
        }
        return new ResponseObjectRequest("ID Fresher found", "Final score: " + finalScore, list);
    }

    @Override
    public Double avgScore(Long fresherId) {
        List<AssignmentScoreResponse.ScoreResponse>
                list = assignmentScoreRepository.findAllByFresherId(fresherId).stream()
                .map(assignmentScore -> AssignmentScoreResponse.ScoreResponse.builder()
                        .assignmentId(assignmentScore.getAssignment().getId())
                        .fresherId(assignmentScore.getFresher().getId())
                        .fresherName(assignmentScore.getFresher().getName())
                        .score(assignmentScore.getScore())
                        .percentage(assignmentScore.getAssignment().getPercentage()).build()).collect(Collectors.toList());
        Double avgScore = 0.0;
        for (AssignmentScoreResponse.ScoreResponse assScore : list) {
            avgScore += assScore.getScore().doubleValue() * assScore.getPercentage().doubleValue() / 100;
        }
        return avgScore;
    }


    @Override
    public ResponseObjectRequest findAllFresherByAvgScore(Long score) {
        List<AssignmentScoreResponse> list = assignmentScoreRepository.findAllByAssignmentId(3l).stream()
                .map(assignmentScore -> AssignmentScoreResponse.builder()
                        .scoreResponseList(findAllAssignmentByFresherId(assignmentScore.getFresher().getId()))
                        .avgScore(avgScore(assignmentScore.getFresher().getId()))
                        .build()).collect(Collectors.toList());

        list = list.stream()
                .filter(fresher -> fresher.getAvgScore() >= score)
                .collect(Collectors.toList());
        long count = list.stream().count();

        return new
                ResponseObjectRequest("Found: " + count,
                count + " fresher have final score greater or equal: " + score, list);
    }
}
