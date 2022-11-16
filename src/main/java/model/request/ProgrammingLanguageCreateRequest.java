package model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ProgrammingLanguageCreateRequest {
    @NotBlank(message = "language name cannot be empty!")
    private String name;

    private String description;
}
