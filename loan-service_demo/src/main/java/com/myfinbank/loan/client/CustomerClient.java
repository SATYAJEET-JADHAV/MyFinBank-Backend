package com.myfinbank.loan.client;

import com.myfinbank.loan.dto.CustomerProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "customer-service")
public interface CustomerClient {

    @GetMapping("/api/customers/{id}")
    CustomerProfileResponse getCustomerById(@PathVariable Long id);
}