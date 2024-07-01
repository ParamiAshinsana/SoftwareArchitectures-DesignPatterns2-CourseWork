package org.example2.ticketservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ticket")
public class TicketServiceController {
    @GetMapping("/ticketService")
    public String getDetail(){
        return "Hello Tickets";
    }
}
