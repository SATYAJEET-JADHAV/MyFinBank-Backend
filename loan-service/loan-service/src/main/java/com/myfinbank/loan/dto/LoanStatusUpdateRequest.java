package com.myfinbank.loan.dto;

import com.myfinbank.loan.enums.LoanStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanStatusUpdateRequest {
    @NotNull(message = "Loan status is required")
    private LoanStatus status;
    private String remarks;
}
