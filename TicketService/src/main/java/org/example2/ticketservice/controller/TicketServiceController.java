package org.example2.ticketservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ticket")
public class TicketServiceController {
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/ticketService")
    public String getDetail(){
        return "Hello Tickets";
    }

    @GetMapping("/ticketService-payment")
    public String testPaymentDetail(){
        return restTemplate.getForObject("https://payment-service/api/v1/payment/paymentService", String.class);
    }
}
