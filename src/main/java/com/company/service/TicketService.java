package com.company.service;

import com.company.model.Ticket;
import com.company.payload.TicketDTO;

import java.util.List;

public interface TicketService {
    int saveTicket(Ticket ticket);
    List<Ticket> alTicket();
    List<TicketDTO> allTicketDTO();
}
