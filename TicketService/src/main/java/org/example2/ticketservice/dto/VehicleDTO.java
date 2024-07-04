package org.example2.ticketservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO {
    private String vehicleRegistrationId;
    private String vehicleType;
    private String fuelType;
    private String vehicleNo;
    private String nameOfOwner;
    private String addressOfOwner;
    private String registeredDate;
}
