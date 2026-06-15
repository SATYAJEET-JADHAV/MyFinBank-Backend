package com.myfinbank.loan.service;

import com.myfinbank.loan.dto.*;

import java.util.List;

public interface LoanApplicationService {

    LoanApplicationResponse applyLoan(LoanApplicationRequest request);

    LoanApplicationResponse getLoanById(Long id);

    List<LoanApplicationResponse> getLoansByCustomerId(Long customerId);

    List<LoanApplicationResponse> getAllLoans();

    LoanApplicationResponse updateLoanStatus(Long id, LoanStatusUpdateRequest request);
}