package com.myfinbank.loan.dto;

import java.math.BigDecimal;

public class EmiResponse {

    private BigDecimal loanAmount;
    private BigDecimal interestRate;
    private Integer tenureMonths;
    private BigDecimal emiAmount;
    private BigDecimal totalPayable;
    private BigDecimal totalInterest;

    public EmiResponse() {
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public Integer getTenureMonths() {
        return tenureMonths;
    }

    public BigDecimal getEmiAmount() {
        return emiAmount;
    }

    public BigDecimal getTotalPayable() {
        return totalPayable;
    }

    public BigDecimal getTotalInterest() {
        return totalInterest;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public void setTenureMonths(Integer tenureMonths) {
        this.tenureMonths = tenureMonths;
    }

    public void setEmiAmount(BigDecimal emiAmount) {
        this.emiAmount = emiAmount;
    }

    public void setTotalPayable(BigDecimal totalPayable) {
        this.totalPayable = totalPayable;
    }

    public void setTotalInterest(BigDecimal totalInterest) {
        this.totalInterest = totalInterest;
    }
}