package model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AssignmentCreateRequest {

    @Min(0)
    @Max(100)
    @NotNull(message = "Percentage cannot be empty!")
    private Integer percentage;

    @NotBlank(message = "Name cannot be empty!")
    private String name;

    private String description;
}
