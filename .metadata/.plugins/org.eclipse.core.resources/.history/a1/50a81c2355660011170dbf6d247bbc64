package com.myfinbank.loan.controller;

import com.myfinbank.loan.dto.LoanDocumentRequest;
import com.myfinbank.loan.service.LoanDocumentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documents")
public class LoanDocumentController {

    private final LoanDocumentService loanDocumentService;

    public LoanDocumentController(LoanDocumentService loanDocumentService) {
        this.loanDocumentService = loanDocumentService;
    }

    @PostMapping
    public ResponseEntity<?> addDocument(@Valid @RequestBody LoanDocumentRequest request) {
        return new ResponseEntity<>(loanDocumentService.addDocument(request), HttpStatus.CREATED);
    }

    @GetMapping("/loan/{loanId}")
    public ResponseEntity<?> getDocumentsByLoanId(@PathVariable Long loanId) {
        return ResponseEntity.ok(loanDocumentService.getDocumentsByLoanId(loanId));
    }
}