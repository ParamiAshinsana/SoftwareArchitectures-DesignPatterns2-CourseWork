package org.example2.ticketservice.controller;

import lombok.RequiredArgsConstructor;
import org.example2.ticketservice.dto.TicketDTO;
import org.example2.ticketservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ticket")
public class TicketServiceController {
    @Autowired
    private RestTemplate restTemplate;

    private final TicketService ticketService;
    @GetMapping("/ticketService")
    public String getDetail(){
        return "Hello Tickets";
    }

    @GetMapping("/ticketService-payment")
    public String testPaymentDetail(){
        return restTemplate.getForObject("https://payment-service/api/v1/payment/paymentService", String.class);
    }

    @PostMapping(value = "/saveUser")
    public TicketDTO saveUser(@RequestBody TicketDTO ticketDTO){
        return ticketService.saveUser(ticketDTO);
    }
}
