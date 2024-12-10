package uz.javadev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.javadev.service.UserService;
import uz.javadev.service.dto.CommonResultData;
import uz.javadev.service.dto.UserDto;

import static uz.javadev.utils.Constants.USERS;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController extends AbstractCrudController<String, UserDto, UserService> {

    private final UserService service;

    public UserController(UserService service) {
        super(service, USERS);
        this.service = service;
    }

    @GetMapping("/get-by-username/{username}")
    public CommonResultData<UserDto> getByUsername(@PathVariable String username) {
        log.info("REQUEST getByUsername {}", username);
        return service.getByUsername(username);
    }
}
