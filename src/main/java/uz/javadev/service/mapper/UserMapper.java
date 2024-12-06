package uz.javadev.crudgenericexample.service.mapper;

import org.mapstruct.Mapper;
import uz.javadev.crudgenericexample.domain.User;
import uz.javadev.crudgenericexample.service.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {
}
