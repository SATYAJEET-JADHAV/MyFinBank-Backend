package com.myfinbank.loan.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanApplicationRequest {

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Vehicle ID is required")
    private Long vehicleId;

    @NotNull(message = "Down payment is required")
    @DecimalMin(value = "0.0", message = "Down payment cannot be negative")
    private BigDecimal downPayment;

    @NotNull(message = "Loan amount is required")
    @DecimalMin(value = "1.0", message = "Loan amount must be greater than 0")
    private BigDecimal loanAmount;

    @NotNull(message = "Interest rate is required")
    @DecimalMin(value = "0.1", message = "Interest rate must be greater than 0")
    private BigDecimal interestRate;

    @NotNull(message = "Tenure months is required")
    @Min(value = 1, message = "Tenure must be at least 1 month")
    private Integer tenureMonths;

    private String remarks;
}