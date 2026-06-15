package com.myfinbank.loan.dto;

public class WorkflowTaskRequest {

    private Long loanId;
    private Long customerId;
    private String assignedRole;
    private String currentStage;
    private String status;

    public WorkflowTaskRequest() {
    }

    public WorkflowTaskRequest(
            Long loanId,
            Long customerId,
            String assignedRole,
            String currentStage,
            String status
    ) {
        this.loanId = loanId;
        this.customerId = customerId;
        this.assignedRole = assignedRole;
        this.currentStage = currentStage;
        this.status = status;
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