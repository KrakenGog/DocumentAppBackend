// Organisation.java
package com.mse.dapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "organisations")
@Data
public class Organisation {
    @Id
    @Column(name = "UNP")
    @Min(value = 100000000, message = "UNP must be 9 digits")
    @Max(value = 999999999, message = "UNP must be 9 digits")
    private Integer unp;
    
    @NotBlank(message = "Organisation name is mandatory")
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "short_name")
    private String shortName;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "legal_address")
    private String legalAddress;
    
    @Column(name = "mail_address")
    private String mailAddress;
    
    @Column(name = "phone_number")
    private String phoneNumber;
}