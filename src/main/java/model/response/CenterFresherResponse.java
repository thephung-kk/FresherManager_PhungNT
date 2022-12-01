package model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CenterFresherResponse {
    private Long centerId;
    private String centerName;
    private Long fresherId;
    private String fresherName;
    private LocalDate startDate;
    private LocalDate endDate;
}
