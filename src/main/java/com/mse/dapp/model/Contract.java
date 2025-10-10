// Contract.java
package com.mse.dapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "contracts")
@Data
public class Contract {
    @Id
    private Integer id;
    
    @NotBlank(message = "Contract number is mandatory")
    @Size(max = 20, message = "Contract number must not exceed 20 characters")
    @Column(name = "number", nullable = false, length = 20, unique = true)
    private String number;
    
    @NotBlank(message = "Contract type is mandatory")
    @Pattern(regexp = "ГРС|Клиент ТК", message = "Contract type must be either 'ГРС' or 'Клиент ТК'")
    @Column(name = "type", nullable = false, length = 20)
    private String type;
    
    @NotNull(message = "Approval date is mandatory")
    @Column(name = "approval_date", nullable = false)
    private LocalDateTime approvalDate;
    
    @NotNull(message = "Begin date is mandatory")
    @Column(name = "begin_date", nullable = false)
    private LocalDateTime beginDate;
    
    @NotNull(message = "End date is mandatory")
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;
    
    @PositiveOrZero(message = "Workplaces count must be greater than or equal to 0")
    @Column(name = "workplaces_count")
    private Integer workplacesCount;
    
    @Min(value = 0, message = "is_electro_acts must be 0 or 1")
    @Max(value = 1, message = "is_electro_acts must be 0 or 1")
    @Column(name = "is_electro_acts")
    private Integer isElectroActs;
    
    @NotBlank(message = "Expiry type is mandatory")
    @Pattern(regexp = "Окончание срока действия|Отказ от заключения|Ликвидация организации", 
             message = "Expiry type must be one of: 'Окончание срока действия', 'Отказ от заключения', 'Ликвидация организации'")
    @Column(name = "expiry_type", nullable = false, length = 20)
    private String expiryType;
    
    @NotNull(message = "Service is mandatory")
    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;
    
    @NotNull(message = "Bank account is mandatory")
    @ManyToOne
    @JoinColumn(name = "bank_account_id", nullable = false)
    private BankAccount bankAccount;
    
    @AssertTrue(message = "Begin date must be before or equal to end date")
    public boolean isBeginDateBeforeEndDate() {
        if (beginDate == null || endDate == null) {
            return true; // Validation will fail on @NotNull first
        }
        return beginDate.isBefore(endDate) || beginDate.isEqual(endDate);
    }
    
    @AssertTrue(message = "Approval date must be before or equal to begin date")
    public boolean isApprovalDateBeforeBeginDate() {
        if (approvalDate == null || beginDate == null) {
            return true; // Validation will fail on @NotNull first
        }
        return approvalDate.isBefore(beginDate) || approvalDate.isEqual(beginDate);
    }
}