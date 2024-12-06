package uz.javadev.crudgenericexample.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.javadev.crudgenericexample.domain.User;
import uz.javadev.crudgenericexample.repository.UserRepository;
import uz.javadev.crudgenericexample.service.dto.UserDto;
import uz.javadev.crudgenericexample.service.filter.UserSpecification;
import uz.javadev.crudgenericexample.service.impl.AbstractCrudServiceImpl;
import uz.javadev.crudgenericexample.service.mapper.UserMapper;

import java.util.UUID;

@Slf4j
@Service
public class UserService extends AbstractCrudServiceImpl<UUID, UserDto, User, UserMapper, UserSpecification, UserRepository> {
    public UserService(UserMapper mapper, UserSpecification specification, UserRepository repository) {
        super(mapper, specification, repository);
    }
}
