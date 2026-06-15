package com.myfinbank.loan.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "loan_documents")
public class LoanDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentId;

    @Column(nullable = false)
    private Long loanId;

    @Column(nullable = false, length = 500)
    private String documentUrl;

    private LocalDateTime uploadedAt;

    public LoanDocument() {
    }

    @PrePersist
    public void onCreate() {
        this.uploadedAt = LocalDateTime.now();
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }
}