// Service.java
package com.mse.dapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "services")
@Data
public class ServiceEntity {
    @Id
    private Integer id;
    
    @NotBlank(message = "Name is mandatory")
    @Size(max = 20, message = "Name must not exceed 20 characters")
    @Column(name = "name", length = 20)
    private String name;
    
    @NotNull(message = "Cost is mandatory")
    @PositiveOrZero(message = "Cost must be greater than or equal to 0")
    @Column(name = "cost")
    private Float cost;
    
    @Size(max = 20, message = "Unit must not exceed 20 characters")
    @Column(name = "unit", length = 20)
    private String unit;
}