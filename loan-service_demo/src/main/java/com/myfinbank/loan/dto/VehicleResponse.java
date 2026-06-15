package com.myfinbank.loan.dto;

import com.myfinbank.loan.enums.VehicleStatus;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleResponse {

    private Long id;
    private String make;
    private String model;
    private String variant;
    private Integer year;
    private String vehicleType;
    private BigDecimal exShowroomPrice;
    private BigDecimal onRoadPrice;
    private VehicleStatus status;
}