package uz.javadev.service.filter;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import uz.javadev.domain.Apartment;
import uz.javadev.service.dto.ApartmentDto;

import java.util.ArrayList;
import java.util.List;

import static uz.javadev.utils.Constants.APARTMENT;

@Component
public class ApartmentSpecification implements AbstractSpecification<Apartment, ApartmentDto> {
    @Override
    public Specification<Apartment> createSpecification(ApartmentDto filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), filter.getId()));
            }
            if (filter.getApartNum() != null) {
                predicates.add(criteriaBuilder.equal(root.get("apartNum"), filter.getApartNum()));
            }
            if (filter.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), filter.getStatus()));
            }
            if (filter.getType() != null) {
                predicates.add(criteriaBuilder.equal(root.get("type"), filter.getType()));
            }
            if (filter.getPrice() != null) {
                predicates.add(criteriaBuilder.equal(root.get("price"), filter.getPrice()));
            }
            if (filter.getFloor() != null) {
                predicates.add(criteriaBuilder.equal(root.get("floor"), filter.getFloor()));
            }
            if (filter.getRoomCount() != null) {
                predicates.add(criteriaBuilder.equal(root.get("roomCount"), filter.getRoomCount()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }

    @Override
    public String getTableName() {
        return APARTMENT;
    }
}
