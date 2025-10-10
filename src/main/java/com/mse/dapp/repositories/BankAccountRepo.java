package com.mse.dapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mse.dapp.model.BankAccount;

public interface BankAccountRepo extends JpaRepository<BankAccount, Long>  {
    
}
