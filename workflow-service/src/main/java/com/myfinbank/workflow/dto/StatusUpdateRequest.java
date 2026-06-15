package com.myfinbank.workflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StatusUpdateRequest {

    @NotNull(message = "Changed by user ID is required")
    private Long changedBy;

    @NotBlank(message = "New status is required")
    private String newStatus;

    private String remarks;

    public StatusUpdateRequest() {
    }

    public Long getChangedBy() {
        return changedBy;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setChangedBy(Long changedBy) {
        this.changedBy = changedBy;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}