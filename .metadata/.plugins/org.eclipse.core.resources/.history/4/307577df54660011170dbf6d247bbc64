package com.myfinbank.loan.service;

import com.myfinbank.loan.dto.LoanApplicationRequest;
import com.myfinbank.loan.dto.LoanApplicationResponse;
import com.myfinbank.loan.dto.LoanStatusUpdateRequest;

import java.util.List;

public interface LoanService {

    LoanApplicationResponse applyLoan(LoanApplicationRequest request);

    LoanApplicationResponse getLoanById(Long loanId);

    List<LoanApplicationResponse> getLoansByCustomerId(Long customerId);

    List<LoanApplicationResponse> getAllLoans();

    List<LoanApplicationResponse> getLoansByStatus(String status);

    LoanApplicationResponse updateLoanStatus(Long loanId, LoanStatusUpdateRequest request);
}