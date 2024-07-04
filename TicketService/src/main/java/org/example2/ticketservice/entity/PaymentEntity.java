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
@Table(name = "payment")
public class PaymentEntity {
    @Id
    private String paymentId;
    private String description;
    private String paymentMethod;
    private double amount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
