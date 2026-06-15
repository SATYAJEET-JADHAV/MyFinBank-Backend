package com.myfinbank.customer.dto;

import com.myfinbank.customer.enums.KycStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KycVerificationRequest {

    @NotNull(message = "KYC status is required")
    private KycStatus kycStatus;
}