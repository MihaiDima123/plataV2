package com.plata.Plata.user.repository;

import com.plata.Plata.user.entity.PermissionGroup;
import com.plata.Plata.user.entity.User;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends CrudRepository<User, Integer> {
    int countByEmail(String email);
    int countByUsername(String username);
    Optional<User> findByUsername(@Nonnull String username);

    @Query("select u.userGroups " +
            "from User u " +
            "where u = :user")
    Set<PermissionGroup> getUserGroups(@Nonnull User user);
}
