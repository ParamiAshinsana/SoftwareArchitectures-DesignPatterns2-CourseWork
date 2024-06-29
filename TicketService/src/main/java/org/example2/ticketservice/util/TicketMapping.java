package org.example2.ticketservice.util;

import lombok.RequiredArgsConstructor;
import org.example2.ticketservice.dto.TicketDTO;
import org.example2.ticketservice.entity.TicketEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TicketMapping {
    private final ModelMapper modelMapper;

    public TicketDTO toTicketDTO(TicketEntity ticketEntity) {
        return  modelMapper.map(ticketEntity, TicketDTO.class);
    }
    public TicketEntity toTicket(TicketDTO userDTO) {
        return  modelMapper.map(userDTO, TicketEntity.class);
    }
    public List<TicketDTO> toTicketDTOList(List<TicketEntity> ticketEntities) {
        return modelMapper.map(ticketEntities, List.class);
    }
}
