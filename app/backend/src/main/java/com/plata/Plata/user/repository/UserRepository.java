package com.plata.Plata.user.repository;

import com.plata.Plata.user.entity.User;
import jakarta.annotation.Nonnull;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    int countByEmail(String email);
    int countByUsername(String username);
    Optional<User> findByUsername(@Nonnull String username);
}
