package model.request;

import com.vmo.FresherManager_PhungNT.entity.Assignment;
import com.vmo.FresherManager_PhungNT.entity.Fresher;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AssignmentScoreCreateRequest {

    @NotBlank(message = "FresherID cannot be empty!")
    private Long fresherID;

    @NotBlank(message = "AssignmentID cannot be empty!")
    private Long assignmentID;

    @NotBlank(message = "Score cannot be empty!")
    @Min(0)
    @Max(10)
    private Integer score;
}
