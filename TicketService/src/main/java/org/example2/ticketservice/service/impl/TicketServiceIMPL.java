package org.example2.ticketservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example2.ticketservice.dto.TicketDTO;
import org.example2.ticketservice.entity.TicketEntity;
import org.example2.ticketservice.repository.TicketDAO;
import org.example2.ticketservice.service.TicketService;
import org.example2.ticketservice.util.TicketMapping;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketServiceIMPL implements TicketService {
    private final TicketDAO ticketDAO;
    private final TicketMapping ticketMapping;

    @Override
    public TicketDTO issueTicketAtEntrance(TicketDTO ticketDTO) {
        // Assign current date if issuedDate is not provided
        if (ticketDTO.getIssuedDate() == null) {
            ticketDTO.setIssuedDate(LocalDate.now());
        }

        // Assign current time if issuedTime is not provided
        if (ticketDTO.getIssuedTime() == null) {
            ticketDTO.setIssuedTime(LocalTime.now());
        }

        TicketEntity ticketEntity = ticketMapping.toTicket(ticketDTO);
        ticketEntity = ticketDAO.save(ticketEntity);
        return ticketMapping.toTicketDTO(ticketEntity);
    }

    @Override
    public void deleteTicket(String id) {

    }

    @Override
    public void issueTicketAtExit(String id, TicketDTO ticketDTO) {

    }

    @Override
    public List<TicketDTO> getAllTicketDetails() {
        return null;
    }

    @Override
    public TicketDTO getSelectedTicketDetails(String id) {
        return null;
    }
}
