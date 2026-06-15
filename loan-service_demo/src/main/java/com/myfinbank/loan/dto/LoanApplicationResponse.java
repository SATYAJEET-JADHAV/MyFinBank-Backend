package com.myfinbank.loan.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LoanApplicationResponse {

    private Long loanId;
    private Long customerId;
    private String vehicleType;
    private BigDecimal vehiclePrice;
    private BigDecimal loanAmount;
    private BigDecimal interestRate;
    private Integer tenureMonths;
    private BigDecimal emiAmount;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public LoanApplicationResponse() {
    }

    public LoanApplicationResponse(
            Long loanId,
            Long customerId,
            String vehicleType,
            BigDecimal vehiclePrice,
            BigDecimal loanAmount,
            BigDecimal interestRate,
            Integer tenureMonths,
            BigDecimal emiAmount,
            String status,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.loanId = loanId;
        this.customerId = customerId;
        this.vehicleType = vehicleType;
        this.vehiclePrice = vehiclePrice;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.tenureMonths = tenureMonths;
        this.emiAmount = emiAmount;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getLoanId() {
        return loanId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public BigDecimal getVehiclePrice() {
        return vehiclePrice;
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

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setVehiclePrice(BigDecimal vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}