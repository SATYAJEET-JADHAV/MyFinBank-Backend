package com.myfinbank.emi.controller;

import com.myfinbank.emi.dto.EmiRequest;
import com.myfinbank.emi.dto.EmiResponse;
import com.myfinbank.emi.service.EmiService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emi")
public class EmiController {

    private final EmiService emiService;

    public EmiController(EmiService emiService) {
        this.emiService = emiService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<EmiResponse> calculateEmi(@Valid @RequestBody EmiRequest request) {
        EmiResponse response = emiService.calculateEmi(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("EMI Service is running");
    }
}