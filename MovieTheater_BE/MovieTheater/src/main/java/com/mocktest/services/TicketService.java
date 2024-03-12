package com.mocktest.services;

import com.mocktest.entities.*;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.repository.SeatRepository;
import com.mocktest.repository.ShowTimeRepository;
import com.mocktest.repository.TicketRepository;
import com.mocktest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final ShowTimeRepository showTimeRepository;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    public TicketService(TicketRepository ticketRepository,
                         ShowTimeRepository showTimeRepository,
                         SeatRepository seatRepository,
                         UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.showTimeRepository = showTimeRepository;
        this.seatRepository = seatRepository;
        this.userRepository = userRepository;
    }

    public List<Ticket> getTicketsByUserId(Long id) {
        List<Ticket> tickets = ticketRepository.getTicketsByUserId(id);

        if (tickets == null) {
            throw new NotFoundException("No data!");
        }
        return tickets;
    }

    public List<Ticket> createTickets(Long userId, Long showtimeId, List<Long> seatIds,
                                      double normalPrice, double vipPrice) {
        double totalAmount = 0.0;
        List<Ticket> tickets = null;
        ShowTime showTimeFromDb = showTimeRepository.findById(showtimeId)
                .orElseThrow(() -> new NotFoundException("No showtime found!"));
        User userFromDb = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("No user found!"));
        for (Long seatId : seatIds) {
            Ticket ticket = new Ticket();
            Seat seatNormal = seatRepository.findByIdAndSeatType(seatId, String.valueOf(SeatType.NORMAL));
            ticket.setSeat(seatNormal);
            ticket.setShowTime(showTimeFromDb);
            ticket.setStartTime(showTimeFromDb.getStartTime());
            ticket.setEndTime(showTimeFromDb.getEndTime());
            ticket.setUser(userFromDb);
            ticket.setTicketType(TicketType.BOOKED);

            totalAmount += normalPrice;

            tickets.add(ticket);

            ticketRepository.saveAll(tickets);
        }
        for (Long seatId : seatIds) {
            Ticket ticket = new Ticket();
            Seat seatVip = seatRepository.findByIdAndSeatType(seatId, String.valueOf(SeatType.VIP));
            ticket.setSeat(seatVip);
            ticket.setShowTime(showTimeFromDb);
            ticket.setStartTime(showTimeFromDb.getStartTime());
            ticket.setEndTime(showTimeFromDb.getEndTime());
            ticket.setUser(userFromDb);
            ticket.setTicketType(TicketType.BOOKED);

            totalAmount += vipPrice;

            tickets.add(ticket);

            ticketRepository.saveAll(tickets);
        }
        return tickets;
    }
}
