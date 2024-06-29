package org.example2.vehicleservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "vehicle")
public class VehicleEntity {
    @Id
    private String vehicleRegistrationId;
    private String vehicleType;
//    private String fuelType;
//    private String vehicleNo;
//    private String nameOfOwner;
//    private String addressOfOwner;
}
