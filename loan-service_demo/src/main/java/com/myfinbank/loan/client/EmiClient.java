package com.myfinbank.loan.client;

import com.myfinbank.loan.dto.EmiRequest;
import com.myfinbank.loan.dto.EmiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "emi-service")
public interface EmiClient {

    @PostMapping("/emi/calculate")
    EmiResponse calculateEmi(EmiRequest request);
}