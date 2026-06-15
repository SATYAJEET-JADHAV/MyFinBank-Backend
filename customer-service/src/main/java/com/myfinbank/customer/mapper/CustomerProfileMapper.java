package com.myfinbank.customer.mapper;

import com.myfinbank.customer.dto.CustomerProfileRequest;
import com.myfinbank.customer.dto.CustomerProfileResponse;
import com.myfinbank.customer.entity.CustomerProfile;
import com.myfinbank.customer.enums.KycStatus;

public class CustomerProfileMapper {

    private CustomerProfileMapper() {
    }

    public static CustomerProfile toEntity(CustomerProfileRequest request) {
        return CustomerProfile.builder()
                .userId(request.getUserId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .gender(request.getGender())
                .email(request.getEmail())
                .mobileNumber(request.getMobileNumber())
                .panNumber(request.getPanNumber())
                .aadhaarNumber(request.getAadhaarNumber())
                .occupation(request.getOccupation())
                .employerName(request.getEmployerName())
                .monthlyIncome(request.getMonthlyIncome())
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .pincode(request.getPincode())
                .creditScore(request.getCreditScore())
                .kycStatus(KycStatus.PENDING)
                .build();
    }

    public static CustomerProfileResponse toResponse(CustomerProfile entity) {
        return CustomerProfileResponse.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .dateOfBirth(entity.getDateOfBirth())
                .gender(entity.getGender())
                .email(entity.getEmail())
                .mobileNumber(entity.getMobileNumber())
                .panNumber(entity.getPanNumber())
                .aadhaarNumber(entity.getAadhaarNumber())
                .occupation(entity.getOccupation())
                .employerName(entity.getEmployerName())
                .monthlyIncome(entity.getMonthlyIncome())
                .address(entity.getAddress())
                .city(entity.getCity())
                .state(entity.getState())
                .pincode(entity.getPincode())
                .creditScore(entity.getCreditScore())
                .kycStatus(entity.getKycStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public static void updateEntity(CustomerProfile entity, CustomerProfileRequest request) {
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setDateOfBirth(request.getDateOfBirth());
        entity.setGender(request.getGender());
        entity.setEmail(request.getEmail());
        entity.setMobileNumber(request.getMobileNumber());
        entity.setPanNumber(request.getPanNumber());
        entity.setAadhaarNumber(request.getAadhaarNumber());
        entity.setOccupation(request.getOccupation());
        entity.setEmployerName(request.getEmployerName());
        entity.setMonthlyIncome(request.getMonthlyIncome());
        entity.setAddress(request.getAddress());
        entity.setCity(request.getCity());
        entity.setState(request.getState());
        entity.setPincode(request.getPincode());
        entity.setCreditScore(request.getCreditScore());
    }
}