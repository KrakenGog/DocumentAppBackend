package com.mse.dapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mse.dapp.model.Organisation;

public interface OrganizationRepo extends JpaRepository<Organisation, Long>  {
    
}