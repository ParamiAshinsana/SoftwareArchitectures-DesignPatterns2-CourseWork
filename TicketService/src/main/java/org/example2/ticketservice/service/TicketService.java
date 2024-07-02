package org.example2.ticketservice.service;

import org.example2.ticketservice.dto.TicketDTO;

import java.util.List;

public interface TicketService {
    TicketDTO issueTicketAtEntrance(TicketDTO ticketDTO);
    void deleteTicket(String id);
    void issueTicketAtExit(String id, TicketDTO ticketDTO);
    List<TicketDTO> getAllTicketDetails();
    TicketDTO getSelectedTicketDetails(String id);
}
