package model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class CenterFresherCreateRequest {
    @NotBlank(message = "CenterID cannot be empty!")
    private Long centerID;

    @NotBlank(message = "FresherID cannot be empty!")
    private Long fresherID;

    @NotNull(message = "Start date cannot be empty!")
    private LocalDate startDate;

    private LocalDate endDate;
}
