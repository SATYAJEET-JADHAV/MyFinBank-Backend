package com.myfinbank.loan.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EligibilityResponse {

    private boolean eligible;
    private String reason;
    private BigDecimal maxEligibleAmount;
}