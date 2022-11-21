package model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class FresherResponse {
    private Long fresherId;
    private String fresherName;
    private LocalDate dob;
    private String address;
    private String phone;
    private String email;
}