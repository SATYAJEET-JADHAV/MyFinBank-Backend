package com.myfinbank.customer.dto;

import com.myfinbank.customer.enums.KycStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerProfileResponse {

    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String email;
    private String mobileNumber;
    private String panNumber;
    private String aadhaarNumber;
    private String occupation;
    private String employerName;
    private BigDecimal monthlyIncome;
    private String address;
    private String city;
    private String state;
    private String pincode;
    private Integer creditScore;
    private KycStatus kycStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}