package uz.javadev.crudgenericexample.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String username;

    @NotBlank
    private String phone;

    private String about;
}
