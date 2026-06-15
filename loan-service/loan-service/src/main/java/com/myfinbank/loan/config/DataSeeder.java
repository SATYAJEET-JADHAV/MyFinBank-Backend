package com.myfinbank.loan.config;

import com.myfinbank.loan.entity.Vehicle;
import com.myfinbank.loan.enums.VehicleStatus;
import com.myfinbank.loan.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;

@Configuration
@RequiredArgsConstructor
public class DataSeeder {
    private final VehicleRepository vehicleRepository;

    @Bean
    public CommandLineRunner seedVehicles() {
        return args -> {
            if (vehicleRepository.count() > 0) return;
            vehicleRepository.save(Vehicle.builder().make("Honda").model("City").variant("VX Petrol").year(2025).vehicleType("CAR").exShowroomPrice(BigDecimal.valueOf(1250000)).onRoadPrice(BigDecimal.valueOf(1450000)).status(VehicleStatus.ACTIVE).build());
            vehicleRepository.save(Vehicle.builder().make("Hyundai").model("Creta").variant("SX Diesel").year(2025).vehicleType("SUV").exShowroomPrice(BigDecimal.valueOf(1450000)).onRoadPrice(BigDecimal.valueOf(1680000)).status(VehicleStatus.ACTIVE).build());
            vehicleRepository.save(Vehicle.builder().make("Tata").model("Nexon").variant("XZ Plus").year(2025).vehicleType("SUV").exShowroomPrice(BigDecimal.valueOf(1100000)).onRoadPrice(BigDecimal.valueOf(1320000)).status(VehicleStatus.ACTIVE).build());
            vehicleRepository.save(Vehicle.builder().make("Mahindra").model("XUV700").variant("AX7").year(2025).vehicleType("SUV").exShowroomPrice(BigDecimal.valueOf(2200000)).onRoadPrice(BigDecimal.valueOf(2550000)).status(VehicleStatus.ACTIVE).build());
            vehicleRepository.save(Vehicle.builder().make("Maruti").model("Brezza").variant("ZXI Plus").year(2025).vehicleType("SUV").exShowroomPrice(BigDecimal.valueOf(1050000)).onRoadPrice(BigDecimal.valueOf(1230000)).status(VehicleStatus.ACTIVE).build());
        };
    }
}
