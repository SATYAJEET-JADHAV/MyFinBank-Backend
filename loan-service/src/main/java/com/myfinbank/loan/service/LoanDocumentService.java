package com.myfinbank.loan.service;

import com.myfinbank.loan.dto.LoanDocumentRequest;
import com.myfinbank.loan.dto.LoanDocumentResponse;

import java.util.List;

public interface LoanDocumentService {

    LoanDocumentResponse addDocument(LoanDocumentRequest request);

    List<LoanDocumentResponse> getDocumentsByLoanId(Long loanId);
}