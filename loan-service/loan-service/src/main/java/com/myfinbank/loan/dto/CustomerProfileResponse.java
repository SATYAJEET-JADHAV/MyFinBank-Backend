package com.myfinbank.loan.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

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
    private String email;
    private String mobileNumber;
    private BigDecimal monthlyIncome;
    private Integer creditScore;
    private String kycStatus;
}
