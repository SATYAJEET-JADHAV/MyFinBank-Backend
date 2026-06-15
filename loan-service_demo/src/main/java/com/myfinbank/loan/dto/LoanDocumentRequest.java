package com.myfinbank.loan.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoanDocumentRequest {

    @NotNull(message = "Loan ID is required")
    private Long loanId;

    @NotBlank(message = "Document URL is required")
    private String documentUrl;

    public LoanDocumentRequest() {
    }

    public Long getLoanId() {
        return loanId;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }
}