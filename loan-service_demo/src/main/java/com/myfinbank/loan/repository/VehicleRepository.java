package com.myfinbank.loan.repository;

import com.myfinbank.loan.entity.Vehicle;
import com.myfinbank.loan.enums.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByStatus(VehicleStatus status);
}