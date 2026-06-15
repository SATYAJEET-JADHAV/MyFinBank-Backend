package com.myfinbank.workflow.client;

import com.myfinbank.workflow.dto.LoanStatusUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "loan-service")
public interface LoanClient {

    @PutMapping("/loans/{loanId}/status")
    Object updateLoanStatus(
            @PathVariable Long loanId,
            @RequestBody LoanStatusUpdateRequest request
    );
}