package com.mocktest.services;

import com.mocktest.entities.Seat;
import com.mocktest.entities.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> getTicketsByUserId(Long id);
    List<Ticket> createTickets(Long userId, Long showtimeId, List<Long> seatIds,
                               double normalPrice, double vipPrice);
}
