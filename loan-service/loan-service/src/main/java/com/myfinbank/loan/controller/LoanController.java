package com.myfinbank.loan.controller;

import com.myfinbank.loan.dto.*;
import com.myfinbank.loan.service.LoanApplicationService;
import com.myfinbank.loan.service.LoanEligibilityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {
    private final LoanApplicationService loanApplicationService;
    private final LoanEligibilityService loanEligibilityService;

    @PostMapping("/check-eligibility")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'LOAN_OFFICER', 'MANAGER', 'ADMIN')")
    public ResponseEntity<EligibilityResponse> checkEligibility(@Valid @RequestBody LoanEligibilityRequest request) {
        return ResponseEntity.ok(loanEligibilityService.checkEligibility(request));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ResponseEntity<LoanApplicationResponse> applyLoan(@Valid @RequestBody LoanApplicationRequest request) {
        return new ResponseEntity<>(loanApplicationService.applyLoan(request), HttpStatus.CREATED);
    }

    @PostMapping("/apply")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ResponseEntity<LoanApplicationResponse> applyLoanOld(@Valid @RequestBody LoanApplicationRequest request) {
        return new ResponseEntity<>(loanApplicationService.applyLoan(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'LOAN_OFFICER', 'MANAGER', 'ADMIN')")
    public ResponseEntity<LoanApplicationResponse> getLoanById(@PathVariable Long id) {
        return ResponseEntity.ok(loanApplicationService.getLoanById(id));
    }

    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'LOAN_OFFICER', 'MANAGER', 'ADMIN')")
    public ResponseEntity<List<LoanApplicationResponse>> getLoansByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(loanApplicationService.getLoansByCustomerId(customerId));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('LOAN_OFFICER', 'MANAGER', 'ADMIN')")
    public ResponseEntity<List<LoanApplicationResponse>> getAllLoans() {
        return ResponseEntity.ok(loanApplicationService.getAllLoans());
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('LOAN_OFFICER', 'MANAGER', 'ADMIN')")
    public ResponseEntity<LoanApplicationResponse> updateLoanStatus(@PathVariable Long id, @Valid @RequestBody LoanStatusUpdateRequest request) {
        return ResponseEntity.ok(loanApplicationService.updateLoanStatus(id, request));
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Loan Service is running");
    }
}
