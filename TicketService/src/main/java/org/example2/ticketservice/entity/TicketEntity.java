package org.example2.ticketservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example2.ticketservice.enumeration.PaymentStatus;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "payment_Id",referencedColumnName = "paymentId")
    private PaymentEntity paymentId;

//    @ManyToOne
//    private VehicleEntity vehicleEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicleNo")
    private VehicleEntity vehicleEntity;
}
