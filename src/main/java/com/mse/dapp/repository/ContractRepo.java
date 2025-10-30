package com.mse.dapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mse.dapp.model.Contract;

public interface ContractRepo extends JpaRepository<Contract, Long>  {
    
}
