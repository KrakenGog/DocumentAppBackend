package com.mse.dapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mse.dapp.model.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
    boolean existsByLogin(String login);
}

