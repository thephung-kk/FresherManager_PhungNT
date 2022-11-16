package model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class CenterCreateRequest {
    @NotBlank(message = "Name cannot be empty!")
    private String name;

    private String code;

    @NotNull(message = "Date of birth cannot be empty!")
    private LocalDate dob;

    @NotBlank(message = "Address cannot be empty!")
    private String address;

}
