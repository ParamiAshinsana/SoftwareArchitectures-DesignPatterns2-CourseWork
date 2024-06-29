package org.example2.vehicleservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example2.vehicleservice.dto.VehicleDTO;
import org.example2.vehicleservice.repository.VehicleDAO;
import org.example2.vehicleservice.service.VehicleService;
import org.example2.vehicleservice.util.VehicleMapping;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceIMPL implements VehicleService {
    private final VehicleMapping vehicleMapping;
    private final VehicleDAO vehicleDAO;

    @Override
    public VehicleDTO saveVehicle(VehicleDTO vehicleDTO) {
        vehicleDTO.setVehicleRegistrationId(UUID.randomUUID().toString());
         return vehicleMapping.toVehicleDTO(vehicleDAO.save(vehicleMapping.toVehicle(vehicleDTO)));
    }

    @Override
    public void deleteVehicle(String id) {

    }

    @Override
    public void updateVehicle(String id, VehicleDTO vehicleDTO) {

    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return null;
    }

    @Override
    public VehicleDTO getSelectedVehicle(String id) {
        return null;
    }
}
