package com.myfinbank.loan.controller;

import com.myfinbank.loan.dto.LoanApplicationRequest;
import com.myfinbank.loan.dto.LoanApplicationResponse;
import com.myfinbank.loan.dto.LoanStatusUpdateRequest;
import com.myfinbank.loan.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/apply")
    public ResponseEntity<?> applyLoan(@Valid @RequestBody LoanApplicationRequest request) {
        return new ResponseEntity<>(loanService.applyLoan(request), HttpStatus.CREATED);
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<?> getLoanById(@PathVariable Long loanId) {
        return ResponseEntity.ok(loanService.getLoanById(loanId));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getLoansByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(loanService.getLoansByCustomerId(customerId));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> getLoansByStatus(@PathVariable String status) {
        return ResponseEntity.ok(loanService.getLoansByStatus(status));
    }

    @PutMapping("/{loanId}/status")
    public ResponseEntity<LoanApplicationResponse> updateLoanStatus(
            @PathVariable Long loanId,
            @Valid @RequestBody LoanStatusUpdateRequest request
    ) {
        return ResponseEntity.ok(loanService.updateLoanStatus(loanId, request));
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Loan Service is running");
    }
}