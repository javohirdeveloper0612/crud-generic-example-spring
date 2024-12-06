package uz.javadev.repository;

import uz.javadev.domain.User;

import java.util.Optional;


public interface UserRepository extends AbstractRepository<User, String> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
