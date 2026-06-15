package com.myfinbank.workflow.dto;

import java.time.LocalDateTime;

public class StatusHistoryResponse {

    private Long historyId;
    private Long loanId;
    private String previousStatus;
    private String newStatus;
    private Long changedBy;
    private LocalDateTime changedAt;

    public StatusHistoryResponse() {
    }

    public StatusHistoryResponse(
            Long historyId,
            Long loanId,
            String previousStatus,
            String newStatus,
            Long changedBy,
            LocalDateTime changedAt
    ) {
        this.historyId = historyId;
        this.loanId = loanId;
        this.previousStatus = previousStatus;
        this.newStatus = newStatus;
        this.changedBy = changedBy;
        this.changedAt = changedAt;
    }

    public Long getHistoryId() {
        return historyId;
    }

    public Long getLoanId() {
        return loanId;
    }

    public String getPreviousStatus() {
        return previousStatus;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public Long getChangedBy() {
        return changedBy;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public void setPreviousStatus(String previousStatus) {
        this.previousStatus = previousStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    public void setChangedBy(Long changedBy) {
        this.changedBy = changedBy;
    }

    public void setChangedAt(LocalDateTime changedAt) {
        this.changedAt = changedAt;
    }
}