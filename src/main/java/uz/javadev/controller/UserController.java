package uz.javadev.crudgenericexample.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.javadev.crudgenericexample.service.UserService;
import uz.javadev.crudgenericexample.service.dto.UserDto;

import static uz.javadev.crudgenericexample.utils.Constants.USERS;

@RestController
@RequestMapping("/api/users")
public class UserController extends AbstractCrudController<String, UserDto, UserService> {
    public UserController(UserService service) {
        super(service, USERS);
    }
}
