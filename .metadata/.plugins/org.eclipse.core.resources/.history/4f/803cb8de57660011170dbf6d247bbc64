package com.myfinbank.workflow.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "workflow_tasks")
public class WorkflowTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @Column(nullable = false)
    private Long loanId;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false, length = 30)
    private String assignedRole;

    @Column(nullable = false, length = 50)
    private String currentStage;

    @Column(nullable = false, length = 30)
    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public WorkflowTask() {
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        if (this.status == null || this.status.isBlank()) {
            this.status = "PENDING";
        }
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getTaskId() {
        return taskId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
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