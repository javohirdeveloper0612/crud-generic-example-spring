package uz.javadev.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.javadev.domain.Apartment;
import uz.javadev.repository.ApartmentRepository;
import uz.javadev.service.dto.ApartmentDto;
import uz.javadev.service.dto.CommonResultData;
import uz.javadev.service.filter.ApartmentSpecification;
import uz.javadev.service.impl.AbstractCrudServiceImpl;
import uz.javadev.service.mapper.ApartmentMapper;

@Slf4j
@Service
public class ApartmentService extends AbstractCrudServiceImpl<String, ApartmentDto, Apartment, ApartmentMapper, ApartmentSpecification, ApartmentRepository> {

    private final ApartmentRepository repo;
    private final ApartmentMapper mapper;

    public ApartmentService(ApartmentMapper mapper,
                            ApartmentSpecification specification,
                            ApartmentRepository repo) {
        super(mapper, specification, repo);
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public CommonResultData<ApartmentDto> create(ApartmentDto request) {
        if (repo.existsByApartNum(request.getApartNum())) {
            return CommonResultData.failed("Apart number already exists");
        }
        return super.create(request);
    }
}
