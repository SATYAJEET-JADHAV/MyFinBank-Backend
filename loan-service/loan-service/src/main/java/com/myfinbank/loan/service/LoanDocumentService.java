package com.myfinbank.loan.service;

import com.myfinbank.loan.dto.LoanDocumentResponse;
import com.myfinbank.loan.enums.DocumentType;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface LoanDocumentService {
    LoanDocumentResponse uploadDocument(Long loanId, DocumentType documentType, MultipartFile file);
    LoanDocumentResponse getDocumentById(Long id);
    List<LoanDocumentResponse> getDocumentsByLoanId(Long loanId);
}
