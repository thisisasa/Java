package com.dinsaren.oneposappserverapi.repository;

import com.dinsaren.oneposappserverapi.models.Role;
import com.dinsaren.oneposappserverapi.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRole name);
}
