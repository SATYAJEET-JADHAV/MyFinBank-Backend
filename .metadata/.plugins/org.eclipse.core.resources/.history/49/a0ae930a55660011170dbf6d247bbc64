package com.myfinbank.loan.service;

import com.myfinbank.loan.dto.LoanDocumentRequest;
import com.myfinbank.loan.dto.LoanDocumentResponse;
import com.myfinbank.loan.entity.LoanDocument;
import com.myfinbank.loan.exception.LoanNotFoundException;
import com.myfinbank.loan.repository.LoanApplicationRepository;
import com.myfinbank.loan.repository.LoanDocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanDocumentServiceImpl implements LoanDocumentService {

    private final LoanDocumentRepository loanDocumentRepository;
    private final LoanApplicationRepository loanApplicationRepository;

    public LoanDocumentServiceImpl(
            LoanDocumentRepository loanDocumentRepository,
            LoanApplicationRepository loanApplicationRepository
    ) {
        this.loanDocumentRepository = loanDocumentRepository;
        this.loanApplicationRepository = loanApplicationRepository;
    }

    @Override
    public LoanDocumentResponse addDocument(LoanDocumentRequest request) {

        if (!loanApplicationRepository.existsById(request.getLoanId())) {
            throw new LoanNotFoundException("Loan not found with ID: " + request.getLoanId());
        }

        LoanDocument loanDocument = new LoanDocument();
        loanDocument.setLoanId(request.getLoanId());
        loanDocument.setDocumentUrl(request.getDocumentUrl());

        LoanDocument savedDocument = loanDocumentRepository.save(loanDocument);

        return mapToResponse(savedDocument);
    }

    @Override
    public List<LoanDocumentResponse> getDocumentsByLoanId(Long loanId) {

        if (!loanApplicationRepository.existsById(loanId)) {
            throw new LoanNotFoundException("Loan not found with ID: " + loanId);
        }

        return loanDocumentRepository.findByLoanId(loanId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private LoanDocumentResponse mapToResponse(LoanDocument loanDocument) {

        return new LoanDocumentResponse(
                loanDocument.getDocumentId(),
                loanDocument.getLoanId(),
                loanDocument.getDocumentUrl(),
                loanDocument.getUploadedAt()
        );
    }
}