package org.example2.vehicleservice.repository;

import org.example2.vehicleservice.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleDAO extends JpaRepository<VehicleEntity, String> {
    Optional<VehicleEntity> findByVehicleTypeAndVehicleNo(String vehicleType, String vehicleNo);

    boolean existsByVehicleTypeAndVehicleNo(String vehicleType, String vehicleNo);
}
