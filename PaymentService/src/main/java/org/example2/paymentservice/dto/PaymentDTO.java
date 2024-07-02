package org.example2.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example2.paymentservice.enumeration.PaymentStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {
    private String paymentId;
    private String description;
    private String paymentMethod;
    private double amount;
    private PaymentStatus paymentStatus;
}
