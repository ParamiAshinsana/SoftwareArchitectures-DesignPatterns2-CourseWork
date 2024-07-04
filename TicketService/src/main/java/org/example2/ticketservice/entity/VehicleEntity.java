package org.example2.ticketservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
//@Table(name = "vehicle")
@Table(name = "vehicle", uniqueConstraints = {@UniqueConstraint(columnNames = {"vehicleType", "vehicleNo"})})
public class VehicleEntity {
    @Id
    private String vehicleRegistrationId;
    private String vehicleType;
    private String fuelType;
    @Column(unique = false)
    private String vehicleNo;
    private String nameOfOwner;
    private String addressOfOwner;
    private String registeredDate;

    @OneToMany(mappedBy = "vehicleEntity", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TicketEntity> ticketEntities ;
}
