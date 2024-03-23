package com.mocktest.services;

import com.mocktest.bean.SeatDetailResponse;
import com.mocktest.bean.SeatRequest;
import com.mocktest.bean.SeatTypeResponse;
import com.mocktest.entities.*;
import com.mocktest.exceptions.BadRequestException;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.repository.SeatRepository;
import com.mocktest.repository.ShowTimeRepository;
import com.mocktest.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        System.out.println(LocalTime.now());
        List<Ticket> tickets = ticketRepository.getAllSeatBookedInTicket(showTimeId, LocalTime.now());
        System.out.println(tickets);
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

    public Seat getById(Long id) {
        Optional<Seat> seatOptional = seatRepository.findById(id);
        Seat requests = seatOptional.orElseThrow(() -> new NotFoundException("User not found with id"));
        return  requests;
    }
    public List<SeatTypeResponse> updateTypeSeatById(List<SeatRequest> requests){
        List<SeatTypeResponse> response = new ArrayList<>();
        for (SeatRequest seatRequest : requests){
            Seat seat = getById(seatRequest.getId());
            seat.setSeatType(seatRequest.getSeatType());
            seat = seatRepository.save(seat);
            SeatTypeResponse seatResponse = new SeatTypeResponse();
            seatResponse.setId(seat.getId());
            seatResponse.setSeatColumn(seat.getSeatColumn());
            seatResponse.setSeatRow(seat.getSeatRow());
            seatResponse.setSeatType(seat.getSeatType());
            response.add(seatResponse);
        }
        return response;
    }
    public List<SeatTypeResponse> getAllSeatByRoom(Long id){
        List<Seat> seats = seatRepository.getAllSeatsByRoom(id);
        List<SeatTypeResponse> responses = new ArrayList<>();
        for (Seat seat : seats){
            SeatTypeResponse seatTypeResponse = new SeatTypeResponse();
            seatTypeResponse.setId(seat.getId());
            seat.setSeatRow(seat.getSeatRow());
            seatTypeResponse.setSeatColumn(seat.getSeatColumn());
            seatTypeResponse.setSeatType(seat.getSeatType());
            responses.add(seatTypeResponse);
        }
        return responses;
    }
}
