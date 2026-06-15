package com.myfinbank.loan.mapper;

import com.myfinbank.loan.dto.LoanApplicationResponse;
import com.myfinbank.loan.entity.LoanApplication;

public class LoanApplicationMapper {
    private LoanApplicationMapper() {}

    public static LoanApplicationResponse toResponse(LoanApplication loan) {
        return LoanApplicationResponse.builder()
                .id(loan.getId())
                .customerId(loan.getCustomerId())
                .vehicleId(loan.getVehicleId())
                .vehiclePrice(loan.getVehiclePrice())
                .downPayment(loan.getDownPayment())
                .loanAmount(loan.getLoanAmount())
                .interestRate(loan.getInterestRate())
                .tenureMonths(loan.getTenureMonths())
                .status(loan.getStatus())
                .remarks(loan.getRemarks())
                .createdAt(loan.getCreatedAt())
                .updatedAt(loan.getUpdatedAt())
                .build();
    }
}
