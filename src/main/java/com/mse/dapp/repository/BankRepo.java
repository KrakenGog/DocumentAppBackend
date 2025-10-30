package com.mse.dapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mse.dapp.model.Bank;

public interface BankRepo extends JpaRepository<Bank, Long>  {
    
}
