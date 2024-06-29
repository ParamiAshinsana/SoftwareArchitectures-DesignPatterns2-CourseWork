package org.example2.vehicleservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "vehicle")
//@Table(name = "vehicle", uniqueConstraints = {@UniqueConstraint(columnNames = {"vehicleType", "vehicleNo"})})
public class VehicleEntity {
    @Id
    private String vehicleRegistrationId;
    private String vehicleType;
    private String fuelType;
//    @Column(unique = false)
    private String vehicleNo;
    private String nameOfOwner;
    private String addressOfOwner;
    private String registeredDate;
}
