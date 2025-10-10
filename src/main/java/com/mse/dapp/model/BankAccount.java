// BankAccount.java
package com.mse.dapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "bank_accounts")
@Data
public class BankAccount {
    @Id
    private Integer id;
    
    @Size(max = 20, message = "Code must not exceed 20 characters")
    @Column(name = "code", length = 20)
    private String code;
    
    @NotNull(message = "is_budget is mandatory")
    @Min(value = 0, message = "is_budget must be 0 or 1")
    @Max(value = 1, message = "is_budget must be 0 or 1")
    @Column(name = "is_budget")
    private Integer isBudget;
    
    @NotNull(message = "Bank is mandatory")
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;
    
    @NotNull(message = "Organisation UNP is mandatory")
    @Min(value = 100000000, message = "Organisation UNP must be 9 digits")
    @Max(value = 999999999, message = "Organisation UNP must be 9 digits")
    @Column(name = "organisation_UNP")
    private Integer organisationUNP;
}