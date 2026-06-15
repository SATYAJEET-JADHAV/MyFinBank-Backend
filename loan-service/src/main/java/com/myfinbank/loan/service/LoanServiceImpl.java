package com.myfinbank.loan.service;

import com.myfinbank.loan.client.EmiClient;
import com.myfinbank.loan.client.WorkflowClient;
import com.myfinbank.loan.dto.*;
import com.myfinbank.loan.entity.LoanApplication;
import com.myfinbank.loan.exception.InvalidLoanApplicationException;
import com.myfinbank.loan.exception.LoanNotFoundException;
import com.myfinbank.loan.repository.LoanApplicationRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanApplicationRepository loanApplicationRepository;
    private final EmiClient emiClient;
    private final WorkflowClient workflowClient;

    public LoanServiceImpl(
            LoanApplicationRepository loanApplicationRepository,
            EmiClient emiClient,
            WorkflowClient workflowClient
    ) {
        this.loanApplicationRepository = loanApplicationRepository;
        this.emiClient = emiClient;
        this.workflowClient = workflowClient;
    }

    @Override
    public LoanApplicationResponse applyLoan(LoanApplicationRequest request) {

        validateLoanRequest(request);

        EmiRequest emiRequest = new EmiRequest(
                request.getLoanAmount(),
                request.getInterestRate(),
                request.getTenureMonths()
        );

        EmiResponse emiResponse = emiClient.calculateEmi(emiRequest);

        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setCustomerId(request.getCustomerId());
        loanApplication.setVehicleType(request.getVehicleType().toUpperCase());
        loanApplication.setVehiclePrice(request.getVehiclePrice());
        loanApplication.setLoanAmount(request.getLoanAmount());
        loanApplication.setInterestRate(request.getInterestRate());
        loanApplication.setTenureMonths(request.getTenureMonths());
        loanApplication.setEmiAmount(emiResponse.getEmiAmount());
        loanApplication.setStatus("PENDING");

        LoanApplication savedLoan = loanApplicationRepository.save(loanApplication);

        createWorkflowTaskSafely(savedLoan);

        return mapToResponse(savedLoan);
    }

    @Override
    public LoanApplicationResponse getLoanById(Long loanId) {

        LoanApplication loanApplication = loanApplicationRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with ID: " + loanId));

        return mapToResponse(loanApplication);
    }

    @Override
    public List<LoanApplicationResponse> getLoansByCustomerId(Long customerId) {

        return loanApplicationRepository.findByCustomerId(customerId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<LoanApplicationResponse> getAllLoans() {

        return loanApplicationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<LoanApplicationResponse> getLoansByStatus(String status) {

        return loanApplicationRepository.findByStatus(status.toUpperCase())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public LoanApplicationResponse updateLoanStatus(Long loanId, LoanStatusUpdateRequest request) {

        LoanApplication loanApplication = loanApplicationRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with ID: " + loanId));

        loanApplication.setStatus(request.getStatus().toUpperCase());

        LoanApplication updatedLoan = loanApplicationRepository.save(loanApplication);

        return mapToResponse(updatedLoan);
    }

    private void validateLoanRequest(LoanApplicationRequest request) {

        if (request.getLoanAmount().compareTo(request.getVehiclePrice()) > 0) {
            throw new InvalidLoanApplicationException("Loan amount cannot be greater than vehicle price");
        }

        BigDecimal minimumDownPayment = request.getVehiclePrice()
                .multiply(BigDecimal.valueOf(0.10));

        BigDecimal actualDownPayment = request.getVehiclePrice()
                .subtract(request.getLoanAmount());

        if (actualDownPayment.compareTo(minimumDownPayment) < 0) {
            throw new InvalidLoanApplicationException("Minimum 10% down payment is required");
        }
    }

    private void createWorkflowTaskSafely(LoanApplication savedLoan) {

        try {
            WorkflowTaskRequest workflowTaskRequest = new WorkflowTaskRequest(
                    savedLoan.getLoanId(),
                    savedLoan.getCustomerId(),
                    "OFFICER",
                    "DOCUMENT_VERIFICATION",
                    "PENDING"
            );

            workflowClient.createWorkflowTask(workflowTaskRequest);

        } catch (FeignException exception) {
            System.out.println("Workflow service not available. Loan saved successfully, workflow task not created yet.");
        } catch (Exception exception) {
            System.out.println("Could not create workflow task: " + exception.getMessage());
        }
    }

    private LoanApplicationResponse mapToResponse(LoanApplication loanApplication) {

        return new LoanApplicationResponse(
                loanApplication.getLoanId(),
                loanApplication.getCustomerId(),
                loanApplication.getVehicleType(),
                loanApplication.getVehiclePrice(),
                loanApplication.getLoanAmount(),
                loanApplication.getInterestRate(),
                loanApplication.getTenureMonths(),
                loanApplication.getEmiAmount(),
                loanApplication.getStatus(),
                loanApplication.getCreatedAt(),
                loanApplication.getUpdatedAt()
        );
    }
}