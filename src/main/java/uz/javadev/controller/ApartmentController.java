package uz.javadev.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.javadev.service.ApartmentService;
import uz.javadev.service.dto.ApartmentDto;

import static uz.javadev.utils.Constants.USERS;

@RestController
@RequestMapping("/api/apartment")
public class ApartmentController extends AbstractCrudController<String, ApartmentDto, ApartmentService> {
    private final ApartmentService service;

    public ApartmentController(ApartmentService service) {
        super(service, USERS);
        this.service = service;
    }
}
