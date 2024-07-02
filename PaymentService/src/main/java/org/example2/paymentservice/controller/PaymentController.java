package org.example2.paymentservice.controller;

import lombok.RequiredArgsConstructor;
import org.example2.paymentservice.dto.PaymentDTO;
import org.example2.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
public class PaymentController {
    @Autowired
    private RestTemplate restTemplate;

    private final PaymentService paymentService;
    @GetMapping("/paymentService")
    public String getDetail(){
        return "Hello";
    }

    @PostMapping(value = "/processThePayment")
    public PaymentDTO processThePayment(@RequestBody PaymentDTO paymentDTO){
        return paymentService.processThePayment(paymentDTO);
    }
}
