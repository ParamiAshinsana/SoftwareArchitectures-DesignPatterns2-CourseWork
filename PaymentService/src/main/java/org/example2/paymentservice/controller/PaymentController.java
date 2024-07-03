package org.example2.paymentservice.controller;

import lombok.RequiredArgsConstructor;
import org.example2.paymentservice.dto.PaymentDTO;
import org.example2.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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

//    @PostMapping(value = "/processThePayment")
//    public PaymentDTO processThePayment(@RequestBody PaymentDTO paymentDTO){
//        return paymentService.processThePayment(paymentDTO);
//    }

    @PostMapping("/processThePayment")
    public ResponseEntity<?> processThePayment(@RequestBody PaymentDTO paymentDTO) {
        List<String> errors = validatePaymentDTO(paymentDTO);

        if (!errors.isEmpty()) {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        PaymentDTO savedPayment = paymentService.processThePayment(paymentDTO);
        return new ResponseEntity<>(savedPayment, HttpStatus.OK);
    }

    private List<String> validatePaymentDTO(PaymentDTO paymentDTO) {
        List<String> errors = new ArrayList<>();

        if (paymentDTO.getPaymentId() == null || paymentDTO.getPaymentId().isEmpty()) {
            errors.add("Payment ID cannot be empty");
        }
        if (paymentDTO.getDescription() == null || paymentDTO.getDescription().isEmpty()) {
            errors.add("Description cannot be empty");
        }
        if (paymentDTO.getPaymentMethod() == null || paymentDTO.getPaymentMethod().isEmpty()) {
            errors.add("Payment method cannot be empty");
        }
        if (paymentDTO.getAmount() <= 0) {
            errors.add("Amount must be greater than 0");
        }
        if (paymentDTO.getPaymentStatus() == null) {
            errors.add("Payment status cannot be null");
        }

        return errors;
    }
}
