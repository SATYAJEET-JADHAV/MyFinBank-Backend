package com.myfinbank.loan.service;

import com.myfinbank.loan.dto.*;
import com.myfinbank.loan.entity.LoanApplication;
import com.myfinbank.loan.entity.Vehicle;
import com.myfinbank.loan.enums.LoanStatus;
import com.myfinbank.loan.exception.InvalidLoanApplicationException;
import com.myfinbank.loan.exception.LoanNotFoundException;
import com.myfinbank.loan.exception.VehicleNotFoundException;
import com.myfinbank.loan.mapper.LoanApplicationMapper;
import com.myfinbank.loan.repository.LoanApplicationRepository;
import com.myfinbank.loan.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanApplicationServiceImpl implements LoanApplicationService {

    private final LoanApplicationRepository loanApplicationRepository;
    private final VehicleRepository vehicleRepository;
    private final LoanEligibilityService loanEligibilityService;

    @Override
    public LoanApplicationResponse applyLoan(LoanApplicationRequest request) {
        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with ID: " + request.getVehicleId()));

        EligibilityResponse eligibilityResponse = loanEligibilityService.checkEligibility(
                LoanEligibilityRequest.builder()
                        .customerId(request.getCustomerId())
                        .vehicleId(request.getVehicleId())
                        .loanAmount(request.getLoanAmount())
                        .build()
        );

        if (!eligibilityResponse.isEligible()) {
            throw new InvalidLoanApplicationException(eligibilityResponse.getReason());
        }

        if (request.getDownPayment().add(request.getLoanAmount()).compareTo(vehicle.getOnRoadPrice()) < 0) {
            throw new InvalidLoanApplicationException("Down payment plus loan amount must cover vehicle price");
        }

        LoanApplication loan = LoanApplication.builder()
                .customerId(request.getCustomerId())
                .vehicleId(request.getVehicleId())
                .vehiclePrice(vehicle.getOnRoadPrice())
                .downPayment(request.getDownPayment())
                .loanAmount(request.getLoanAmount())
                .interestRate(request.getInterestRate())
                .tenureMonths(request.getTenureMonths())
                .status(LoanStatus.SUBMITTED)
                .remarks(request.getRemarks())
                .build();

        return LoanApplicationMapper.toResponse(loanApplicationRepository.save(loan));
    }

    @Override
    public LoanApplicationResponse getLoanById(Long id) {
        LoanApplication loan = loanApplicationRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with ID: " + id));

        return LoanApplicationMapper.toResponse(loan);
    }

    @Override
    public List<LoanApplicationResponse> getLoansByCustomerId(Long customerId) {
        return loanApplicationRepository.findByCustomerId(customerId)
                .stream()
                .map(LoanApplicationMapper::toResponse)
                .toList();
    }

    @Override
    public List<LoanApplicationResponse> getAllLoans() {
        return loanApplicationRepository.findAll()
                .stream()
                .map(LoanApplicationMapper::toResponse)
                .toList();
    }

    @Override
    public LoanApplicationResponse updateLoanStatus(Long id, LoanStatusUpdateRequest request) {
        LoanApplication loan = loanApplicationRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with ID: " + id));

        loan.setStatus(request.getStatus());

        if (request.getRemarks() != null && !request.getRemarks().isBlank()) {
            loan.setRemarks(request.getRemarks());
        }

        return LoanApplicationMapper.toResponse(loanApplicationRepository.save(loan));
    }
}