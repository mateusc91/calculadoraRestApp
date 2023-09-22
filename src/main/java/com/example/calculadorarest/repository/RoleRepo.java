package com.example.calculadorarest.repository;

import com.example.calculadorarest.entity.Role;
import com.example.calculadorarest.utils.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
