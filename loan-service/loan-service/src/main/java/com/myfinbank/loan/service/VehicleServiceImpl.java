package com.myfinbank.loan.service;

import com.myfinbank.loan.dto.VehicleRequest;
import com.myfinbank.loan.dto.VehicleResponse;
import com.myfinbank.loan.entity.Vehicle;
import com.myfinbank.loan.enums.VehicleStatus;
import com.myfinbank.loan.exception.VehicleNotFoundException;
import com.myfinbank.loan.mapper.VehicleMapper;
import com.myfinbank.loan.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;

    @Override
    public VehicleResponse createVehicle(VehicleRequest request) {
        Vehicle vehicle = VehicleMapper.toEntity(request);
        return VehicleMapper.toResponse(vehicleRepository.save(vehicle));
    }

    @Override
    public VehicleResponse updateVehicle(Long id, VehicleRequest request) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with ID: " + id));
        VehicleMapper.updateEntity(vehicle, request);
        return VehicleMapper.toResponse(vehicleRepository.save(vehicle));
    }

    @Override
    public void deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with ID: " + id));
        vehicle.setStatus(VehicleStatus.INACTIVE);
        vehicleRepository.save(vehicle);
    }

    @Override
    public List<VehicleResponse> getAllVehicles() {
        return vehicleRepository.findAll().stream().map(VehicleMapper::toResponse).toList();
    }

    @Override
    public VehicleResponse getVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with ID: " + id));
        return VehicleMapper.toResponse(vehicle);
    }
}
