package com.myfinbank.loan.service;

import com.myfinbank.loan.dto.EligibilityResponse;
import com.myfinbank.loan.dto.LoanEligibilityRequest;

public interface LoanEligibilityService {
    EligibilityResponse checkEligibility(LoanEligibilityRequest request);
}
