package org.example2.ticketservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example2.ticketservice.enumeration.PaymentStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ticket")
public class TicketEntity {
    @Id
    private String tellerId;
    private String issuedDate;
    private String issuedTime;
    private String entranceIC;
    private String exitIC;
    private int vehicleType;
    private String vehicleNo;
    private String averageSpeed;
    private String travelTime;
    private double amount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
