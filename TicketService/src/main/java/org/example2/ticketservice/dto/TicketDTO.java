package org.example2.ticketservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketDTO {
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
    private String paymentStatus;
}
