package com.mse.dapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mse.dapp.model.Contract;

public interface ContractRepo extends JpaRepository<Contract, Long>  {
    
}
