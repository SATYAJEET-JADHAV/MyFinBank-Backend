package com.myfinbank.customer.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerProfileRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    private String gender;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(regexp = "^[6-9][0-9]{9}$", message = "Invalid mobile number")
    private String mobileNumber;

    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "Invalid PAN number")
    private String panNumber;

    @Pattern(regexp = "^[0-9]{12}$", message = "Invalid Aadhaar number")
    private String aadhaarNumber;

    private String occupation;

    private String employerName;

    @NotNull(message = "Monthly income is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Monthly income must be greater than 0")
    private BigDecimal monthlyIncome;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @Pattern(regexp = "^[0-9]{6}$", message = "Invalid pincode")
    private String pincode;

    @NotNull(message = "Credit score is required")
    @Min(value = 300, message = "Credit score must be at least 300")
    @Max(value = 900, message = "Credit score must be at most 900")
    private Integer creditScore;
}