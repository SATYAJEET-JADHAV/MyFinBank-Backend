package com.myfinbank.loan.dto;

import jakarta.validation.constraints.NotBlank;

public class LoanStatusUpdateRequest {

    @NotBlank(message = "Status is required")
    private String status;

    public LoanStatusUpdateRequest() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}