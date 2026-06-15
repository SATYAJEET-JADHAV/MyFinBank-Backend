package com.myfinbank.workflow.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "loan_status_history")
public class LoanStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @Column(nullable = false)
    private Long loanId;

    @Column(length = 30)
    private String previousStatus;

    @Column(nullable = false, length = 30)
    private String newStatus;

    @Column(nullable = false)
    private Long changedBy;

    private LocalDateTime changedAt;

    public LoanStatusHistory() {
    }

    @PrePersist
    public void onCreate() {
        this.changedAt = LocalDateTime.now();
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
}