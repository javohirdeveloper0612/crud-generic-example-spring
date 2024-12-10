package uz.javadev.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.javadev.domain.Apartment;
import uz.javadev.repository.ApartmentRepository;
import uz.javadev.service.dto.ApartmentDto;
import uz.javadev.service.filter.ApartmentSpecification;
import uz.javadev.service.impl.AbstractCrudServiceImpl;
import uz.javadev.service.mapper.ApartmentMapper;

@Slf4j
@Service
public class ApartmentService extends AbstractCrudServiceImpl<String, ApartmentDto, Apartment, ApartmentMapper, ApartmentSpecification, ApartmentRepository> {

    private final ApartmentRepository repository;
    private final ApartmentMapper mapper;

    public ApartmentService(ApartmentMapper mapper,
                            ApartmentSpecification specification,
                            ApartmentRepository repository) {
        super(mapper, specification, repository);
        this.repository = repository;
        this.mapper = mapper;
    }
}
