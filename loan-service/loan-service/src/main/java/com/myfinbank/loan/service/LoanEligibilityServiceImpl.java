package com.myfinbank.loan.service;

import com.myfinbank.loan.client.CustomerClient;
import com.myfinbank.loan.dto.CustomerProfileResponse;
import com.myfinbank.loan.dto.EligibilityResponse;
import com.myfinbank.loan.dto.LoanEligibilityRequest;
import com.myfinbank.loan.entity.Vehicle;
import com.myfinbank.loan.exception.VehicleNotFoundException;
import com.myfinbank.loan.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

@Service
@RequiredArgsConstructor
public class LoanEligibilityServiceImpl implements LoanEligibilityService {
    private final CustomerClient customerClient;
    private final VehicleRepository vehicleRepository;

    @Override
    public EligibilityResponse checkEligibility(LoanEligibilityRequest request) {
        CustomerProfileResponse customer = customerClient.getCustomerById(request.getCustomerId());
        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with ID: " + request.getVehicleId()));

        BigDecimal maxEligibleAmount = vehicle.getOnRoadPrice().multiply(BigDecimal.valueOf(0.80)).setScale(2, RoundingMode.HALF_UP);

        if (customer.getMonthlyIncome() == null || customer.getMonthlyIncome().compareTo(BigDecimal.valueOf(25000)) < 0) {
            return EligibilityResponse.builder().eligible(false).reason("Monthly income must be at least 25000").maxEligibleAmount(maxEligibleAmount).build();
        }

        int age = Period.between(customer.getDateOfBirth(), LocalDate.now()).getYears();
        if (age < 21 || age > 60) {
            return EligibilityResponse.builder().eligible(false).reason("Customer age must be between 21 and 60").maxEligibleAmount(maxEligibleAmount).build();
        }

        if (customer.getCreditScore() == null || customer.getCreditScore() < 650) {
            return EligibilityResponse.builder().eligible(false).reason("Credit score must be at least 650").maxEligibleAmount(maxEligibleAmount).build();
        }

        if (request.getLoanAmount().compareTo(maxEligibleAmount) > 0) {
            return EligibilityResponse.builder().eligible(false).reason("Loan amount cannot exceed 80% of vehicle price").maxEligibleAmount(maxEligibleAmount).build();
        }

        return EligibilityResponse.builder().eligible(true).reason("Customer is eligible for loan").maxEligibleAmount(maxEligibleAmount).build();
    }
}
