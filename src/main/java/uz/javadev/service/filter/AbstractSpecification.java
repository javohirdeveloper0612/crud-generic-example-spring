package uz.javadev.crudgenericexample.service.filter;

import org.springframework.data.jpa.domain.Specification;

public interface AbstractSpecification<ENTITY, DTO> {
    Specification<ENTITY> createSpecification(DTO filter);
    String getTableName();
}
