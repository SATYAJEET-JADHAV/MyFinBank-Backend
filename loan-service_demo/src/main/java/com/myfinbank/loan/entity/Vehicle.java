package com.myfinbank.loan.entity;

import com.myfin VehicleStatus status;import com.myfinbank.loan.enums.VehicleStatus;
}
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    private String variant;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private String vehicleType;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal exShowroomPrice;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal onRoadPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
