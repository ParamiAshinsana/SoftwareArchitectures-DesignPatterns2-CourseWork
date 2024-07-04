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

import static org.example2.paymentservice.enumeration.PaymentStatus.PAID;
import static org.example2.paymentservice.enumeration.PaymentStatus.PENDING;

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
        List<String> errors = validateEntrancePaymentDTO(paymentDTO);

        if (!errors.isEmpty()) {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        PaymentDTO savedPayment = paymentService.processThePayment(paymentDTO);
        return new ResponseEntity<>(savedPayment, HttpStatus.OK);
    }

    private List<String> validateEntrancePaymentDTO(PaymentDTO paymentDTO) {
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
        if (paymentDTO.getAmount() != 0) {
            errors.add("You can't make payments at Entrance , Pay only at Exit !");
        }
        if (paymentDTO.getPaymentStatus() == null || paymentDTO.getPaymentStatus() != PENDING) {
            errors.add("Payment status cannot be null and Your payment can only be completed at Exit !");
        }

        return errors;
    }


    @PutMapping(value = "/modifyThePayment/{id}")
    public void modifyThePayment(@RequestBody PaymentDTO paymentDTO, @PathVariable ("id") String id){
        paymentService.modifyThePayment(id,paymentDTO);
        System.out.println("Payment Updated!");
    }

    @DeleteMapping(value = "/deleteVehicle/{id}")
    public void deletePayment(@PathVariable ("id") String id){
        paymentService.deletePayment(id);
    }

    @GetMapping(value = "/getAllVehicles")
    List<PaymentDTO> getAllPaymentDetails(){
        return paymentService.getAllPaymentDetails();
    }

    @GetMapping("/getSelectedVehicle/{id}")
    ResponseEntity<PaymentDTO> getSelectedPaymentDetails(@PathVariable ("id") String id){
        PaymentDTO selectedTicket = paymentService.getSelectedPaymentDetails(id);
        return selectedTicket != null ? ResponseEntity.ok(selectedTicket) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
