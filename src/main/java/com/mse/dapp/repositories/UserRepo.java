package com.mse.dapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mse.dapp.model.User;

public interface UserRepo extends JpaRepository<User, Long>  {
    
}
