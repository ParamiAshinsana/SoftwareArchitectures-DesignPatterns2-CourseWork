package org.example2.ticketservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example2.ticketservice.enumeration.PaymentStatus;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ticket")
public class TicketEntity {
    @Id
    private String tellerId;
    private LocalDate issuedDate;
    private LocalTime issuedTime;
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
