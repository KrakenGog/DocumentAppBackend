package com.mse.dapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mse.dapp.model.Organisation;

public interface OrganizationRepo extends JpaRepository<Organisation, Long>  {
    
}