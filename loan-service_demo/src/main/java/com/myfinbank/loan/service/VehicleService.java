package com.myfinbank.loan.service;

import com.myfinbank.loan.dto.VehicleRequest;
import com.myfinbank.loan.dto.VehicleResponse;

import java.util.List;

public interface VehicleService {

    VehicleResponse createVehicle(VehicleRequest request);

    VehicleResponse updateVehicle(Long id, VehicleRequest request);

    void deleteVehicle(Long id);

    List<VehicleResponse> getAllVehicles();

    VehicleResponse getVehicleById(Long id);
}