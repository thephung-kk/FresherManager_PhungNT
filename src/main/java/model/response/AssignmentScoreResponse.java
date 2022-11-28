package model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AssignmentScoreResponse {
    private Double avgScore;
    private List<ScoreResponse> scoreResponseList;

    @Getter
    @Setter
    @Builder
    public static class ScoreResponse{
        private Long assignmentId;
        private Long fresherId;
        private Integer score;
        private Integer percentage;
        private String fresherName;
    }
}
