package org.example2.vehicleservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example2.vehicleservice.dto.VehicleDTO;
import org.example2.vehicleservice.entity.VehicleEntity;
import org.example2.vehicleservice.exception.DuplicateVehicleException;
import org.example2.vehicleservice.exception.NotFoundException;
import org.example2.vehicleservice.repository.VehicleDAO;
import org.example2.vehicleservice.service.VehicleService;
import org.example2.vehicleservice.util.VehicleMapping;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceIMPL implements VehicleService {
    private final VehicleMapping vehicleMapping;
    private final VehicleDAO vehicleDAO;

    @Override
    public VehicleDTO saveVehicle(VehicleDTO vehicleDTO) {
//         vehicleDTO.setVehicleRegistrationId(UUID.randomUUID().toString());
//         return vehicleMapping.toVehicleDTO(vehicleDAO.save(vehicleMapping.toVehicle(vehicleDTO)));

// Check if the vehicleType and vehicleNo combination already exists
        if (vehicleDAO.existsByVehicleTypeAndVehicleNo(vehicleDTO.getVehicleType(), vehicleDTO.getVehicleNo())) {
            throw new DuplicateVehicleException("A vehicle with the same type and number already exists");
        }

        vehicleDTO.setVehicleRegistrationId(UUID.randomUUID().toString());
        return vehicleMapping.toVehicleDTO(vehicleDAO.save(vehicleMapping.toVehicle(vehicleDTO)));
        // Check if the vehicleType and vehicleNo combination already exists
//        if (vehicleDAO.existsByVehicleTypeAndVehicleNo(vehicleDTO.getVehicleType(), vehicleDTO.getVehicleNo())) {
//            throw new IllegalArgumentException("A vehicle with the same type and number already exists");
//        }
//
//        vehicleDTO.setVehicleRegistrationId(UUID.randomUUID().toString());
//        return vehicleMapping.toVehicleDTO(vehicleDAO.save(vehicleMapping.toVehicle(vehicleDTO)));
    }

    @Override
    public void deleteVehicle(String id) {
        if(!vehicleDAO.existsById(id)) throw new NotFoundException("Vehicle not found");
        vehicleDAO.deleteById(id);
    }

    @Override
    public void updateVehicle(String id, VehicleDTO vehicleDTO) {
        Optional<VehicleEntity> tmpVehicle = vehicleDAO.findById(id);
        if (!tmpVehicle.isPresent()) throw new NotFoundException("Vehicle not found");

        tmpVehicle.get().setVehicleType(vehicleDTO.getVehicleType());
        tmpVehicle.get().setFuelType(vehicleDTO.getFuelType());
        tmpVehicle.get().setVehicleNo(vehicleDTO.getVehicleNo());
        tmpVehicle.get().setNameOfOwner(vehicleDTO.getNameOfOwner());
        tmpVehicle.get().setAddressOfOwner(vehicleDTO.getAddressOfOwner());
        tmpVehicle.get().setRegisteredDate(vehicleDTO.getRegisteredDate());

    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return vehicleMapping.toVehicleDTOList(vehicleDAO.findAll());
    }

    @Override
    public VehicleDTO getSelectedVehicle(String id) {
        if(!vehicleDAO.existsById(id)) throw new NotFoundException("Vehicle not found");
        return vehicleMapping.toVehicleDTO(vehicleDAO.getReferenceById(id));
    }
}
