package com.myfinbank.loan.dto;

import com.myfinbank.loan.enums.LoanStatus;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanApplicationResponse {
    private Long id;
    private Long customerId;
    private Long vehicleId;
    private BigDecimal vehiclePrice;
    private BigDecimal downPayment;
    private BigDecimal loanAmount;
    private BigDecimal interestRate;
    private Integer tenureMonths;
    private LoanStatus status;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
