package com.mse.dapp.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.mse.dapp.model.ServiceEntity;

public interface ServiceRepo extends JpaRepository<ServiceEntity, Long>  {
    
}
