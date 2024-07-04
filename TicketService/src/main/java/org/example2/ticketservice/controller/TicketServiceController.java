package org.example2.ticketservice.controller;

import lombok.RequiredArgsConstructor;
import org.example2.ticketservice.dto.TicketDTO;
import org.example2.ticketservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.example2.ticketservice.enumeration.PaymentStatus.PENDING;

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
        List<String> errors = validateEntranceTicketIssuedDTO(ticketDTO);

        if (!errors.isEmpty()) {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        TicketDTO savedPayment = ticketService.issueTicketAtEntrance(ticketDTO);
        return new ResponseEntity<>(savedPayment, HttpStatus.OK);
    }


    private List<String> validateEntranceTicketIssuedDTO(TicketDTO ticketDTO) {
        List<String> errors = new ArrayList<>();

        if (ticketDTO.getTellerId() == null || ticketDTO.getTellerId().isEmpty()) {
            errors.add("Teller ID cannot be empty");
        }
        if (ticketDTO.getEntranceIC() == null || ticketDTO.getEntranceIC().isEmpty()) {
            errors.add("EntranceIC cannot be empty");
        }
        if (ticketDTO.getExitIC() == null || ticketDTO.getExitIC().isEmpty()) {
            errors.add("ExitIC cannot be empty");
        } else if (!"TRAVELING".equals(ticketDTO.getExitIC())) {
            errors.add("Incorrect information about the Exit!");
        }
        if (ticketDTO.getVehicleType() == 0) {
            errors.add("Vehicle Type cannot be 0");
        }
        if (ticketDTO.getVehicleNo() == null || ticketDTO.getVehicleNo().isEmpty()) {
            errors.add("Vehicle No cannot be empty !");
        }
        if (ticketDTO.getAverageSpeed() == null || ticketDTO.getAverageSpeed().isEmpty()) {
            errors.add("Average Speed cannot be empty!");
        }
        if (ticketDTO.getTravelTime() == null || ticketDTO.getTravelTime().isEmpty()) {
            errors.add("Travel Time information cannot be empty");
        } else if (!"CALCULATING".equals(ticketDTO.getTravelTime())) {
            errors.add("Incorrect information about the Travel Time!");
        }
        if (ticketDTO.getAmount() != 0) {
            errors.add("You can't make payments at Entrance , Pay only at Exit !");
        }
        if (ticketDTO.getPaymentStatus() == null || ticketDTO.getPaymentStatus() != PENDING) {
            errors.add("Payment status cannot be null and Your payment can only be completed at Exit !");
        }

        return errors;
    }

    @PostMapping("/entranceIssuedTicketPayment")
    public String entranceProcessPaymentForTicket(){
        return restTemplate.getForObject("https://payment-service/api/v1/payment/processThePayment", String.class);
    }


    @PutMapping(value = "/issueTicketAtExit/{id}")
    public void issueTicketAtExit(@RequestBody TicketDTO ticketDTO, @PathVariable ("id") String id){
        ticketService.issueTicketAtExit(id,ticketDTO);
        System.out.println("User Updated!");
    }

    @DeleteMapping(value = "/deleteTicket/{id}")
    public void deleteTicket(@PathVariable ("id") String id){
        ticketService.deleteTicket(id);
    }

    @GetMapping(value = "/getAllTicketDetails")
    List<TicketDTO> getAllTicketDetails(){
        return ticketService.getAllTicketDetails();
    }

    @GetMapping("/getSelectedTicketDetails/{id}")
    ResponseEntity<TicketDTO> getSelectedTicketDetails(@PathVariable ("id") String id){
        TicketDTO selectedTicket = ticketService.getSelectedTicketDetails(id);
        return selectedTicket != null ? ResponseEntity.ok(selectedTicket) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
