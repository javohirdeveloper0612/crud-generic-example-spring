package uz.javadev.repository;

import uz.javadev.domain.Apartment;

public interface ApartmentRepository extends AbstractRepository<Apartment, String> {
    boolean existsByApartNum(Integer apartNum);
}
