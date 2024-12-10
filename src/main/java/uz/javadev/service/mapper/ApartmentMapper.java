package uz.javadev.service.mapper;

import org.mapstruct.Mapper;
import uz.javadev.domain.Apartment;
import uz.javadev.service.dto.ApartmentDto;

@Mapper(componentModel = "spring")
public interface ApartmentMapper extends EntityMapper<ApartmentDto, Apartment> {
}
