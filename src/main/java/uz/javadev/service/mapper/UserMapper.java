package uz.javadev.service.mapper;

import org.mapstruct.Mapper;
import uz.javadev.domain.User;
import uz.javadev.service.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {
}
