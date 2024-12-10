package uz.javadev.service.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import uz.javadev.domain.enums.ApartType;

import java.util.UUID;
@Data
@Accessors(chain = true)
public class ApartmentDto {
    private UUID id;

    private Integer apartNum;

    private Integer floor;

    private Integer roomCount;

    private ApartType type;

    private Boolean status;

    private Double price;
}
