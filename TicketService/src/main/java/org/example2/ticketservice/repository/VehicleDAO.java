package org.example2.ticketservice.repository;

import org.example2.ticketservice.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleDAO extends JpaRepository<VehicleEntity, String> {
//    Optional<VehicleEntity> findByVehicleNo(String vehicleNo);
    List<VehicleEntity> findByVehicleNo(String vehicleNo);
}
