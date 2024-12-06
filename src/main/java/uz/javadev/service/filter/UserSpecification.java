package uz.javadev.service.filter;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import uz.javadev.domain.User;
import uz.javadev.service.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

import static uz.javadev.utils.Constants.USERS;

@Component
public class UserSpecification implements AbstractSpecification<User, UserDto> {
    @Override
    public Specification<User> createSpecification(UserDto filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getFirstName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("firstName"), filter.getFirstName()));
            }
            if (filter.getLastName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("lastName"), filter.getLastName()));
            }
            if (filter.getUsername() != null) {
                predicates.add(criteriaBuilder.equal(root.get("username"), filter.getUsername()));
            }
            if (filter.getPhone() != null) {
                predicates.add(criteriaBuilder.equal(root.get("phone"), filter.getPhone()));
            }
            if (filter.getAbout() != null) {
                predicates.add(criteriaBuilder.like(root.get("about"), filter.getAbout()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public String getTableName() {
        return USERS;
    }
}
