package com.myfinbank.loan.controller;

import com.myfinbank.loan.dto.LoanDocumentResponse;
import com.myfinbank.loan.enums.DocumentType;
import com.myfinbank.loan.service.LoanDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class LoanDocumentController {
    private final LoanDocumentService loanDocumentService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('CUSTOMER', 'LOAN_OFFICER', 'ADMIN')")
    public ResponseEntity<LoanDocumentResponse> uploadDocument(
            @RequestParam Long loanId,
            @RequestParam DocumentType documentType,
            @RequestParam MultipartFile file
    ) {
        return new ResponseEntity<>(loanDocumentService.uploadDocument(loanId, documentType, file), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'LOAN_OFFICER', 'MANAGER', 'ADMIN')")
    public ResponseEntity<LoanDocumentResponse> getDocumentById(@PathVariable Long id) {
        return ResponseEntity.ok(loanDocumentService.getDocumentById(id));
    }

    @GetMapping("/loan/{loanId}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'LOAN_OFFICER', 'MANAGER', 'ADMIN')")
    public ResponseEntity<List<LoanDocumentResponse>> getDocumentsByLoanId(@PathVariable Long loanId) {
        return ResponseEntity.ok(loanDocumentService.getDocumentsByLoanId(loanId));
    }
}
