package com.myfinbank.emi.service;

import com.myfinbank.emi.dto.EmiRequest;
import com.myfinbank.emi.dto.EmiResponse;
import com.myfinbank.emi.exception.InvalidEmiInputException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class EmiServiceImpl implements EmiService {

    @Override
    public EmiResponse calculateEmi(EmiRequest request) {

        BigDecimal loanAmount = request.getLoanAmount();
        BigDecimal annualInterestRate = request.getInterestRate();
        Integer tenureMonths = request.getTenureMonths();

        validateInput(loanAmount, annualInterestRate, tenureMonths);

        BigDecimal monthlyInterestRate = annualInterestRate
                .divide(BigDecimal.valueOf(1200), 10, RoundingMode.HALF_UP);

        BigDecimal onePlusRate = BigDecimal.ONE.add(monthlyInterestRate);

        BigDecimal powerValue = onePlusRate.pow(tenureMonths);

        BigDecimal numerator = loanAmount
                .multiply(monthlyInterestRate)
                .multiply(powerValue);

        BigDecimal denominator = powerValue.subtract(BigDecimal.ONE);

        BigDecimal emiAmount = numerator
                .divide(denominator, 2, RoundingMode.HALF_UP);

        BigDecimal totalPayable = emiAmount
                .multiply(BigDecimal.valueOf(tenureMonths))
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal totalInterest = totalPayable
                .subtract(loanAmount)
                .setScale(2, RoundingMode.HALF_UP);

        return new EmiResponse(
                loanAmount.setScale(2, RoundingMode.HALF_UP),
                annualInterestRate.setScale(2, RoundingMode.HALF_UP),
                tenureMonths,
                emiAmount,
                totalPayable,
                totalInterest
        );
    }

    private void validateInput(
            BigDecimal loanAmount,
            BigDecimal interestRate,
            Integer tenureMonths
    ) {
        if (loanAmount == null || loanAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidEmiInputException("Loan amount must be greater than 0");
        }

        if (interestRate == null || interestRate.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidEmiInputException("Interest rate must be greater than 0");
        }

        if (tenureMonths == null || tenureMonths <= 0) {
            throw new InvalidEmiInputException("Tenure months must be greater than 0");
        }
    }
}