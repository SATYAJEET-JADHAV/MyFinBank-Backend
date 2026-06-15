package com.myfinbank.customer.service;

import com.myfinbank.customer.dto.CustomerProfileRequest;
import com.myfinbank.customer.dto.CustomerProfileResponse;
import com.myfinbank.customer.dto.KycVerificationRequest;
import com.myfinbank.customer.entity.CustomerProfile;
import com.myfinbank.customer.exception.CustomerAlreadyExistsException;
import com.myfinbank.customer.exception.CustomerNotFoundException;
import com.myfinbank.customer.mapper.CustomerProfileMapper;
import com.myfinbank.customer.repository.CustomerProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerProfileServiceImpl implements CustomerProfileService {

    private final CustomerProfileRepository customerProfileRepository;

    @Override
    public CustomerProfileResponse createCustomer(CustomerProfileRequest request) {
        validateUniqueFields(request);

        CustomerProfile profile = CustomerProfileMapper.toEntity(request);
        CustomerProfile savedProfile = customerProfileRepository.save(profile);

        return CustomerProfileMapper.toResponse(savedProfile);
    }

    @Override
    public CustomerProfileResponse getCustomerById(Long id) {
        CustomerProfile profile = customerProfileRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer profile not found with ID: " + id));

        return CustomerProfileMapper.toResponse(profile);
    }

    @Override
    public CustomerProfileResponse getCustomerByUserId(Long userId) {
        CustomerProfile profile = customerProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer profile not found with user ID: " + userId));

        return CustomerProfileMapper.toResponse(profile);
    }

    @Override
    public CustomerProfileResponse updateCustomer(Long id, CustomerProfileRequest request) {
        CustomerProfile profile = customerProfileRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer profile not found with ID: " + id));

        CustomerProfileMapper.updateEntity(profile, request);

        CustomerProfile updatedProfile = customerProfileRepository.save(profile);

        return CustomerProfileMapper.toResponse(updatedProfile);
    }

    @Override
    public CustomerProfileResponse verifyKyc(Long id, KycVerificationRequest request) {
        CustomerProfile profile = customerProfileRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer profile not found with ID: " + id));

        profile.setKycStatus(request.getKycStatus());

        CustomerProfile updatedProfile = customerProfileRepository.save(profile);

        return CustomerProfileMapper.toResponse(updatedProfile);
    }

    @Override
    public List<CustomerProfileResponse> getAllCustomers() {
        return customerProfileRepository.findAll()
                .stream()
                .map(CustomerProfileMapper::toResponse)
                .toList();
    }

    private void validateUniqueFields(CustomerProfileRequest request) {
        if (customerProfileRepository.existsByUserId(request.getUserId())) {
            throw new CustomerAlreadyExistsException("Customer already exists with user ID: " + request.getUserId());
        }

        if (customerProfileRepository.existsByEmail(request.getEmail())) {
            throw new CustomerAlreadyExistsException("Customer already exists with email: " + request.getEmail());
        }

        if (customerProfileRepository.existsByMobileNumber(request.getMobileNumber())) {
            throw new CustomerAlreadyExistsException("Customer already exists with mobile number: " + request.getMobileNumber());
        }

        if (customerProfileRepository.existsByPanNumber(request.getPanNumber())) {
            throw new CustomerAlreadyExistsException("Customer already exists with PAN number: " + request.getPanNumber());
        }

        if (customerProfileRepository.existsByAadhaarNumber(request.getAadhaarNumber())) {
            throw new CustomerAlreadyExistsException("Customer already exists with Aadhaar number: " + request.getAadhaarNumber());
        }
    }
}