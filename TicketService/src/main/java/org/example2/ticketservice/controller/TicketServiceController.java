package org.example2.ticketservice.controller;

import lombok.RequiredArgsConstructor;
import org.example2.ticketservice.dto.TicketDTO;
import org.example2.ticketservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

//    @PostMapping(value = "/issueTicketAtEntrance")
//    public TicketDTO issueTicketAtEntrance(@RequestBody TicketDTO ticketDTO){
//        return ticketService.issueTicketAtEntrance(ticketDTO);
//    }

    @PostMapping("/issueTicketAtEntrance")
    public ResponseEntity<?> issueTicketAtEntrance(@RequestBody TicketDTO ticketDTO) {
        List<String> errors = validateEntrancePaymentDTO(ticketDTO);

        if (!errors.isEmpty()) {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        TicketDTO savedPayment = ticketService.issueTicketAtEntrance(ticketDTO);
        return new ResponseEntity<>(savedPayment, HttpStatus.OK);
    }
}
