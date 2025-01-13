package org.example2.ticketservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example2.ticketservice.dto.TicketDTO;
import org.example2.ticketservice.entity.PaymentEntity;
import org.example2.ticketservice.entity.TicketEntity;
import org.example2.ticketservice.entity.VehicleEntity;
import org.example2.ticketservice.exception.NotFoundException;
import org.example2.ticketservice.repository.PaymentDAO;
import org.example2.ticketservice.repository.TicketDAO;
import org.example2.ticketservice.repository.VehicleDAO;
import org.example2.ticketservice.service.TicketService;
import org.example2.ticketservice.util.TicketMapping;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketServiceIMPL implements TicketService {
    private final TicketDAO ticketDAO;
    private final TicketMapping ticketMapping;
    private final VehicleDAO vehicleDAO;
    private final PaymentDAO paymentDAO;

    @Override
    public TicketDTO issueTicketAtEntrance(TicketDTO ticketDTO) {
        // Validate and ensure the vehicleNo exists in the vehicle table
        List<VehicleEntity> vehicles = vehicleDAO.findByVehicleNo(ticketDTO.getVehicleNo());

        if (vehicles.size() != 1) {
            throw new IllegalStateException("Expected one result but found " + vehicles.size());
        }

        VehicleEntity vehicle = vehicles.get(0);

        // Assign current date if issuedDate is not provided
        if (ticketDTO.getIssuedDate() == null) {
            ticketDTO.setIssuedDate(LocalDate.now());
        }

        // Assign current time if issuedTime is not provided
        if (ticketDTO.getIssuedTime() == null) {
            ticketDTO.setIssuedTime(LocalTime.now());
        }

        // Map TicketDTO to TicketEntity
        TicketEntity ticketEntity = ticketMapping.toTicket(ticketDTO);
        ticketEntity.setVehicleEntity(vehicle);


        // Set the payment entity based on the provided paymentId
        PaymentEntity paymentEntity = paymentDAO.findById(ticketDTO.getPaymentId())
                .orElseThrow(() -> new IllegalArgumentException("Payment ID not found: " + ticketDTO.getPaymentId()));
        ticketEntity.setPaymentEntity(paymentEntity);

        // Save the ticket entity
        ticketEntity = ticketDAO.save(ticketEntity);

        // Map the saved TicketEntity back to TicketDTO
        return ticketMapping.toTicketDTO(ticketEntity);
    }

//    @Override
//    public TicketDTO issueTicketAtEntrance(TicketDTO ticketDTO) {
//        // Assign current date if issuedDate is not provided
//        if (ticketDTO.getIssuedDate() == null) {
//            ticketDTO.setIssuedDate(LocalDate.now());
//        }
//
//        // Assign current time if issuedTime is not provided
//        if (ticketDTO.getIssuedTime() == null) {
//            ticketDTO.setIssuedTime(LocalTime.now());
//        }
//
//        TicketEntity ticketEntity = ticketMapping.toTicket(ticketDTO);
//        ticketEntity = ticketDAO.save(ticketEntity);
//        return ticketMapping.toTicketDTO(ticketEntity);
//
//    }

    @Override
    public void deleteTicket(String id) {
        if(!ticketDAO.existsById(id)) throw new NotFoundException("Ticket not found");
        ticketDAO.deleteById(id);
    }

    @Override
    public void issueTicketAtExit(String id, TicketDTO ticketDTO) {
//        Optional<TicketEntity> tmpTicket = ticketDAO.findById(id);
//        if (!tmpTicket.isPresent()) throw new NotFoundException("Ticket not found");
//
//        TicketEntity ticketEntity = tmpTicket.get();
//
//        // Check and set issuedDate and issuedTime
//        if (ticketDTO.getIssuedDate() == null) {
//            ticketDTO.setIssuedDate(LocalDate.now());
//        }
//        if (ticketDTO.getIssuedTime() == null) {
//            ticketDTO.setIssuedTime(LocalTime.now());
//        }
//        ticketEntity.setIssuedDate(ticketDTO.getIssuedDate());
//        ticketEntity.setIssuedTime(ticketDTO.getIssuedTime());
//
//        // Update other fields
//        ticketEntity.setTellerId(ticketDTO.getTellerId());
//        ticketEntity.setEntranceIC(ticketDTO.getEntranceIC());
//        ticketEntity.setExitIC(ticketDTO.getExitIC());
//        ticketEntity.setVehicleType(ticketDTO.getVehicleType());
//        ticketEntity.setVehicleNo(ticketDTO.getVehicleNo());
//        ticketEntity.setAverageSpeed(ticketDTO.getAverageSpeed());
//        ticketEntity.setTravelTime(ticketDTO.getTravelTime());
//        ticketEntity.setAmount(ticketDTO.getAmount());
//        ticketEntity.setPaymentStatus(ticketDTO.getPaymentStatus());
//
//        // Save the updated ticket entity back to the database
//        ticketDAO.save(ticketEntity);

        Optional<TicketEntity> tmpTicket = ticketDAO.findById(id);
        if (!tmpTicket.isPresent()) throw new NotFoundException("Ticket not found");

        TicketEntity ticketEntity = tmpTicket.get();

        // Validate entranceIC
        if (!ticketEntity.getEntranceIC().equals(ticketDTO.getEntranceIC())) {
            throw new IllegalArgumentException("EntranceIC does not match the original record");
        }


        // Validate paymentID
        if (!ticketEntity.getPaymentEntity().getPaymentId().equals(ticketDTO.getPaymentId())) {
            throw new IllegalArgumentException("PaymentID does not match the original record");
        }

        // Validate vehicleType
        if (ticketEntity.getVehicleType() != ticketDTO.getVehicleType()) {
            throw new IllegalArgumentException("Vehicle type does not match the original record");
        }


        // Validate vehicleNo
        if (!ticketEntity.getVehicleNo().equals(ticketDTO.getVehicleNo())) {
            throw new IllegalArgumentException("EntranceIC does not match the original record");
        }



        // Check and set issuedDate and issuedTime
        if (ticketDTO.getIssuedDate() == null) {
            ticketDTO.setIssuedDate(LocalDate.now());
        }
        if (ticketDTO.getIssuedTime() == null) {
            ticketDTO.setIssuedTime(LocalTime.now());
        }
        ticketEntity.setIssuedDate(ticketDTO.getIssuedDate());
        ticketEntity.setIssuedTime(ticketDTO.getIssuedTime());

        // Update other fields except tellerId and entranceIC
        ticketEntity.setExitIC(ticketDTO.getExitIC());
        ticketEntity.setVehicleType(ticketDTO.getVehicleType());
        ticketEntity.setVehicleNo(ticketDTO.getVehicleNo());
        ticketEntity.setAverageSpeed(ticketDTO.getAverageSpeed());
        ticketEntity.setTravelTime(ticketDTO.getTravelTime());
        ticketEntity.setAmount(ticketDTO.getAmount());
        ticketEntity.setPaymentStatus(ticketDTO.getPaymentStatus());

        // Save the updated ticket entity back to the database
        ticketDAO.save(ticketEntity);
    }


    @Override
    public List<TicketDTO> getAllTicketDetails() {
        return ticketMapping.toTicketDTOList(ticketDAO.findAll());
    }

    @Override
    public TicketDTO getSelectedTicketDetails(String id) {
        if(!ticketDAO.existsById(id)) throw new NotFoundException("Ticket not found");
        return ticketMapping.toTicketDTO(ticketDAO.getReferenceById(id));
    }

    @Override
    public boolean isValidEntranceIC(String id, String entranceIC) {
        Optional<TicketEntity> tmpTicket = ticketDAO.findById(id);
        if (!tmpTicket.isPresent()) throw new NotFoundException("Ticket not found");

        TicketEntity ticketEntity = tmpTicket.get();
        return ticketEntity.getEntranceIC().equals(entranceIC);
    }

    @Override
    public boolean isValidPaymentId(String ticketId, String paymentID) {
        Optional<PaymentEntity> tmpPayment = paymentDAO.findById(paymentID); // This line is corrected
        if (!tmpPayment.isPresent()) throw new NotFoundException("Payment not found");

        PaymentEntity paymentEntity = tmpPayment.get();
        return paymentEntity.getPaymentId().equals(paymentID);
    }

    @Override
    public boolean isValidVehicleType(String id, String vehicleType) {
        Optional<TicketEntity> tmpTicket = ticketDAO.findById(id);
        if (!tmpTicket.isPresent()) throw new NotFoundException("VehicleType not found");

        TicketEntity ticketEntity = tmpTicket.get();
        return Integer.compare(ticketEntity.getVehicleType(), Integer.parseInt(vehicleType)) == 0;
    }

    @Override
    public boolean isValidVehicleNo(String id, String vehicleNo) {
        Optional<TicketEntity> tmpVehicleNo = ticketDAO.findById(id);
        if (!tmpVehicleNo.isPresent()) throw new NotFoundException("Vehicle Number not found");

        TicketEntity ticketEntity = tmpVehicleNo.get();
        return ticketEntity.getVehicleNo().equals(vehicleNo);
    }


}
