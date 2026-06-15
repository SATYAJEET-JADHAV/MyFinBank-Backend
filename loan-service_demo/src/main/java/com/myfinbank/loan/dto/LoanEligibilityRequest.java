package com.myfinbank.loan.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanEligibilityRequest {

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Vehicle ID is required")
    private Long vehicleId;

    @NotNull(message = "Loan amount is required")
    @DecimalMin(value = "1.0", message = "Loan amount must be greater than 0")
    private BigDecimal loanAmount;
}