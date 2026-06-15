package com.myfinbank.customer.service;

import com.myfinbank.customer.dto.CustomerProfileRequest;
import com.myfinbank.customer.dto.CustomerProfileResponse;
import com.myfinbank.customer.dto.KycVerificationRequest;

import java.util.List;

public interface CustomerProfileService {

    CustomerProfileResponse createCustomer(CustomerProfileRequest request);

    CustomerProfileResponse getCustomerById(Long id);

    CustomerProfileResponse getCustomerByUserId(Long userId);

    CustomerProfileResponse updateCustomer(Long id, CustomerProfileRequest request);

    CustomerProfileResponse verifyKyc(Long id, KycVerificationRequest request);

    List<CustomerProfileResponse> getAllCustomers();
}