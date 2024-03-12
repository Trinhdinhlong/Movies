package com.mocktest.services;

import com.mocktest.dto.BookingDto;
import com.mocktest.dto.SeatDto;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    public List<BookingDto> getAllBookings(){
        List<BookingDto> bookingDtoList = ticketRepository.getAllBookings();
        return bookingDtoList;
    }
    public List<SeatDto> getAllSeatBookedInRoom(Long request) throws NotFoundException {
        List<SeatDto> response = ticketRepository.findBookedSeatsByRoomId(request);
        return response;
    }
}
