package com.myfinbank.workflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class WorkflowTaskRequest {

    @NotNull(message = "Loan ID is required")
    private Long loanId;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotBlank(message = "Assigned role is required")
    private String assignedRole;

    @NotBlank(message = "Current stage is required")
    private String currentStage;

    @NotBlank(message = "Status is required")
    private String status;

    public WorkflowTaskRequest() {
    }

    public Long getLoanId() {
        return loanId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getAssignedRole() {
        return assignedRole;
    }

    public String getCurrentStage() {
        return currentStage;
    }

    public String getStatus() {
        return status;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setAssignedRole(String assignedRole) {
        this.assignedRole = assignedRole;
    }

    public void setCurrentStage(String currentStage) {
        this.currentStage = currentStage;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}