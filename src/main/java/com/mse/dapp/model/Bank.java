// Bank.java
package com.mse.dapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "banks")
@Data
public class Bank {
    @Id
    private Integer id;
    
    @NotBlank(message = "BIC is mandatory")
    @Pattern(regexp = "^[A-Z0-9]+$", message = "BIC must contain only uppercase letters and numbers")
    @Size(min = 8, max = 8, message = "BIC must be exactly 8 characters")
    @Column(name = "BIC", length = 8)
    private String bic;
    
    @NotBlank(message = "Bank name is mandatory")
    @Column(name = "name")
    private String name;
}