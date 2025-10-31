package com.mse.dapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mse.dapp.model.Role;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
