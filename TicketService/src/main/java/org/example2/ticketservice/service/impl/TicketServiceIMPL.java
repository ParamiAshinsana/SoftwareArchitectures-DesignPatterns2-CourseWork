package org.example2.ticketservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example2.ticketservice.service.TicketService;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketServiceIMPL implements TicketService {
}
