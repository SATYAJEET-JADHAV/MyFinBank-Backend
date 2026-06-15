package com.myfinbank.customer.controller;

import com.myfinbank.customer.dto.CustomerProfileRequest;
import com.myfinbank.customer.dto.CustomerProfileResponse;
import com.myfinbank.customer.dto.KycVerificationRequest;
import com.myfinbank.customer.service.CustomerProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerProfileService customerProfileService;

    @PostMapping
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ResponseEntity<CustomerProfileResponse> createCustomer(
            @Valid @RequestBody CustomerProfileRequest request
    ) {
        return new ResponseEntity<>(customerProfileService.createCustomer(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'LOAN_OFFICER', 'MANAGER', 'ADMIN')")
    public ResponseEntity<CustomerProfileResponse> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerProfileService.getCustomerById(id));
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'LOAN_OFFICER', 'MANAGER', 'ADMIN')")
    public ResponseEntity<CustomerProfileResponse> getCustomerByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(customerProfileService.getCustomerByUserId(userId));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ResponseEntity<CustomerProfileResponse> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerProfileRequest request
    ) {
        return ResponseEntity.ok(customerProfileService.updateCustomer(id, request));
    }

    @PutMapping("/{id}/verify")
    @PreAuthorize("hasAnyRole('LOAN_OFFICER', 'ADMIN')")
    public ResponseEntity<CustomerProfileResponse> verifyKyc(
            @PathVariable Long id,
            @Valid @RequestBody KycVerificationRequest request
    ) {
        return ResponseEntity.ok(customerProfileService.verifyKyc(id, request));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('LOAN_OFFICER', 'MANAGER', 'ADMIN')")
    public ResponseEntity<List<CustomerProfileResponse>> getAllCustomers() {
        return ResponseEntity.ok(customerProfileService.getAllCustomers());
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Customer Service is running");
    }
}