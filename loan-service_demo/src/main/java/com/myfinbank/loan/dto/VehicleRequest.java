package com.myfinbank.loan.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleRequest {

    @NotBlank(message = "Make is required")
    private String make;

    @NotBlank(message = "Model is required")
    private String model;

    private String variant;

    @NotNull(message = "Year is required")
    @Min(value = 2000, message = "Year must be valid")
    private Integer year;

    @NotBlank(message = "Vehicle type is required")
    private String vehicleType;

    @NotNull(message = "Ex-showroom price is required")
    @DecimalMin(value = "1.0", message = "Ex-showroom price must be greater than 0")
    private BigDecimal exShowroomPrice;

    @NotNull(message = "On-road price is required")
    @DecimalMin(value = "1.0", message = "On-road price must be greater than 0")
    private BigDecimal onRoadPrice;
}