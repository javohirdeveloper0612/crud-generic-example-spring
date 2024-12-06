package uz.javadev.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.javadev.domain.User;
import uz.javadev.repository.UserRepository;
import uz.javadev.service.dto.CommonResultData;
import uz.javadev.service.dto.UserDto;
import uz.javadev.service.filter.UserSpecification;
import uz.javadev.service.impl.AbstractCrudServiceImpl;
import uz.javadev.service.mapper.UserMapper;

@Slf4j
@Service
public class UserService extends AbstractCrudServiceImpl<String, UserDto, User, UserMapper, UserSpecification, UserRepository> {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserMapper mapper, UserSpecification specification, UserRepository repository, UserRepository repository1, UserMapper mapper1) {
        super(mapper, specification, repository);
        this.repository = repository1;
        this.mapper = mapper1;
    }

    @Override
    public CommonResultData<UserDto> create(UserDto request) {
        if (repository.existsByUsername(request.getUsername()))
            return CommonResultData.failed("username already exists");
        return super.create(request);
    }

    public CommonResultData<UserDto> getByUsername(String username) {
        var result = repository.findByUsername(username)
                .orElse(null);
        if (result == null)
            return CommonResultData.failed("User not found");
        return CommonResultData.success(mapper.toDto(result));
    }
}
