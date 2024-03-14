package com.mocktest.services;

import com.mocktest.bean.SeatDetailResponse;
import com.mocktest.entities.Seat;
import com.mocktest.entities.Ticket;
import com.mocktest.exceptions.BadRequestException;
import com.mocktest.repository.SeatRepository;
import com.mocktest.repository.ShowTimeRepository;
import com.mocktest.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService {
    @Autowired
    private ShowTimeRepository showTimeRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private TicketRepository ticketRepository;
    public List<SeatDetailResponse> getAllSeatByMovieAndRoom( Long roomId, Long movieId, Long showTimeId) {
        if (!showTimeRepository.existsByIdAndMovieIdAndRoomId(showTimeId,movieId, roomId)) {
            throw new BadRequestException("Room Id and Movie Id not correct");
        }
        List<Seat> seats = seatRepository.getAllSeatsByRoom(roomId);
        List<Ticket> tickets = ticketRepository.getAllSeatBookedInTicket(showTimeId, LocalDateTime.now());
        List<SeatDetailResponse> responses = new ArrayList<>();
        for (Seat seat : seats) {
            SeatDetailResponse response = new SeatDetailResponse();
            response.setId(seat.getId());
            response.setSeatColumn(seat.getSeatColumn());
            response.setSeatRow(seat.getSeatRow());
            response.setPrice(seat.getPrice());
            for(Ticket ticket : tickets) {
                if (seat.getId() == ticket.getSeat().getId()) {
                    response.setAvailable(false);
                    break;
                } else {
                    response.setAvailable(true);
                }
            }
            response.setSeatType(seat.getSeatType());
            responses.add(response);
        }
        return responses;
    }


}
