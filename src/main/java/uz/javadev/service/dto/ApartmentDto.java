package uz.javadev.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;
import uz.javadev.domain.enums.ApartType;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class ApartmentDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @NotBlank
    private Integer apartNum;

    @NotBlank
    private Integer floor;

    @NotBlank
    private Integer roomCount;

    @NotBlank
    private ApartType type;

    @NotBlank
    private Boolean status;

    @NotBlank
    private Double price;
}
